package com.zx.card.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.AssetHistoryDao;
import com.zx.card.dao.CardInfoDao;
import com.zx.card.dao.FacultySpecialtyDao;
import com.zx.card.dao.TeacherStudentDao;
import com.zx.card.enums.EventEnums;
import com.zx.card.enums.RoleEnum;
import com.zx.card.enums.StatusEnum;
import com.zx.card.model.*;
import com.zx.card.service.ICardInfoService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardInfoServiceImpl implements ICardInfoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CardInfoDao cardInfoDao;
    @Autowired
    private TeacherStudentDao teacherStudentDao;
    @Autowired
    private FacultySpecialtyDao facultySpecialtyDao;
    @Autowired
    private AssetHistoryDao assetHistoryDao;

    @Override
    public Result selectCardInfoByPage(Page<CardInfo> page, CardInfo cardInfo) {
        try {
            CardInfoExample example = new CardInfoExample();
            CardInfoExample.Criteria criteria = example.createCriteria();
            if (cardInfo.getId() != null) {
                criteria.andIdEqualTo(cardInfo.getId());
            }
            if (cardInfo.getUserId() != null) {
                criteria.andUserIdEqualTo(cardInfo.getUserId());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<CardInfo> list = cardInfoDao.selectByExample(example);
            PageInfo<CardInfo> infos = new PageInfo<>(list);
            return Result.ok("", infos);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveCardInfo(CardInfo cardInfo) {
        try {
            CardInfo oldInfo = cardInfoDao.selectCardInfoByUserId(cardInfo.getUserId());
            if(oldInfo != null){
                return Result.error("已开通一卡通，不需要办卡了");
            }
            if(cardInfo.getAssetAmount() <= 0){
                return Result.error("开卡金额不能为0");
            }
            String password = SecureUtil.md5(cardInfo.getPassword());
            cardInfo.setPassword(password);
            cardInfoDao.insertSelective(cardInfo);
            //添加充值信息
            AssetHistory assetHistory = new AssetHistory();
            assetHistory.setCardId(cardInfo.getId());
            assetHistory.setEvent(EventEnums.deposit.getCode());
            assetHistory.setPayAmt(cardInfo.getAssetAmount());
            assetHistoryDao.insertSelective(assetHistory);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public CardInfo selectCardInfo(Long id) {
        try {
            if (id == null || id <= 0) {
                return null;
            }
            return cardInfoDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateCardInfo(CardInfo cardInfo){
        try {
            if (cardInfo.getId() == null || cardInfo.getId() <= 0) {
                return Result.error("参数错误");
            }
            CardInfo oldCardInfo = cardInfoDao.selectByPrimaryKey(cardInfo.getId());
            if(oldCardInfo == null){
                return Result.error("参数错误");
            }
            if(cardInfo.getAssetAmount() <= 0){
                return Result.error("充值金额不合法");
            }
            BigDecimal amount = NumberUtil.round(cardInfo.getAssetAmount()+oldCardInfo.getAssetAmount(),2);
            oldCardInfo.setAssetAmount(amount.floatValue());
            cardInfoDao.updateByPrimaryKeySelective(oldCardInfo);
            //新增充值记录
            AssetHistory assetHistory = new AssetHistory();
            assetHistory.setEvent(EventEnums.deposit.getCode());
            assetHistory.setCardId(oldCardInfo.getId());
            assetHistory.setPayAmt(cardInfo.getAssetAmount());
            assetHistoryDao.insertSelective(assetHistory);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result lostCardInfo(Long id) {
        try {
            CardInfo cardInfo = cardInfoDao.selectByPrimaryKey(id);
            if(cardInfo == null){
                return Result.error("参数错误");
            }
            if(cardInfo.getStatus().equals(StatusEnum.is_delete.getCode())){
                cardInfo.setStatus(StatusEnum.no_delete.getCode());
            }else{
                cardInfo.setStatus(StatusEnum.is_delete.getCode());
            }
            cardInfoDao.updateByPrimaryKeySelective(cardInfo);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result selectUserInfoById(Long id,String role) {
        try {
            if(id == null || id.intValue() <= 0){
                return Result.error("编号有误");
            }
            CardInfo cardInfo = cardInfoDao.selectCardInfoByUserId(id);
            if(cardInfo != null){
                return Result.error("该学生/教师已开通一卡通");
            }
            TeacherStudentExample example = new TeacherStudentExample();
            example.createCriteria().andRoleEqualTo(role).andIdEqualTo(id);
            List<TeacherStudent> teacherStudent = teacherStudentDao.selectByExample(example);
            if(teacherStudent == null || teacherStudent.size() <= 0){
                if(RoleEnum.student.getCode().equals(role)) {
                    return Result.error("学生信息不存在，请重新输入");
                }else{
                    return Result.error("教师信息不存在，请重新输入");
                }
            }
            if (StatusEnum.is_delete.equals(teacherStudent.get(0).getStatus())) {
                return Result.error("当前学生/教师已被下线");
            }
            //查询院系专业等信息
            teacherStudent.get(0).setSpecialty(facultySpecialtyDao.selectByPrimaryKey(teacherStudent.get(0).getSpecialtyId()));
            teacherStudent.get(0).setFaculty(facultySpecialtyDao.selectByPrimaryKey(teacherStudent.get(0).getFacultyId()));
            Result result = Result.ok("");
            result.put("data",teacherStudent.get(0));
            return result;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result selectCardInfoById(Long id) {
        try {
            if(id == null || id.intValue() <= 0){
                return Result.error("参数错误");
            }
            CardInfo cardInfo = cardInfoDao.selectByPrimaryKey(id);
            if(cardInfo == null){
                return Result.error("卡片信息未找到");
            }
            Result result = Result.ok();
            result.put("data",cardInfo);
            return result;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

}
