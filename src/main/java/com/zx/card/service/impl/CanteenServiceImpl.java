package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.CanteenDao;
import com.zx.card.model.Canteen;
import com.zx.card.model.CanteenExample;
import com.zx.card.service.ICanteenService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenServiceImpl implements ICanteenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CanteenDao canteenDao;

    @Override
    public Result selectCanteenByPage(Page<Canteen> page, Canteen canteen) {
        try {
            CanteenExample example = new CanteenExample();
            CanteenExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(canteen.getLeader())) {
                criteria.andLeaderEqualTo(canteen.getLeader());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<Canteen> list = canteenDao.selectByExample(example);
            PageInfo<Canteen> infos = new PageInfo<>(list);
            return Result.ok("", infos);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveCanteen(Canteen canteen) {
        try {
            if (StringUtils.isBlank(canteen.getLeader())) {
                return Result.error("领导人不能为空");
            }
            canteenDao.insertSelective(canteen);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Canteen selectCanteen(Long id) {
        try {
            if (id == null || id <= 0) {
                return null;
            }
            return canteenDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateCanteen(Canteen canteen) {
        try {
            if (canteen.getId() == null || canteen.getId() <= 0) {
                return Result.error("参数错误");
            }
            canteenDao.updateByPrimaryKeySelective(canteen);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
