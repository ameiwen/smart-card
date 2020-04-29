package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.BookDao;
import com.zx.card.dao.BorrowRecordDao;
import com.zx.card.dao.CardInfoDao;
import com.zx.card.enums.BookEnum;
import com.zx.card.enums.EventEnums;
import com.zx.card.model.Book;
import com.zx.card.model.BorrowRecord;
import com.zx.card.model.BorrowRecordExample;
import com.zx.card.model.CardInfo;
import com.zx.card.service.IBookRecordService;
import com.zx.card.utils.Result;
import com.zx.card.utils.ShiroUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRecordServiceImpl implements IBookRecordService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BorrowRecordDao borrowRecordDao;
    @Autowired
    private CardInfoDao cardInfoDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Result selectBorrowRecordByPage(Page<BorrowRecord> pageInfo, BorrowRecord borrowRecord) {
        try {
            BorrowRecordExample example = new BorrowRecordExample();
            BorrowRecordExample.Criteria criteria = example.createCriteria();
            if(borrowRecord.getCardId() != null){
                criteria.andCardIdEqualTo(borrowRecord.getCardId());
            }
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<BorrowRecord> list = borrowRecordDao.selectByExample(example);
            PageInfo<BorrowRecord> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result selectCardInfoById(Long id) {
        try {
            if(id == null || id <= 0){
                return Result.error("参数错误");
            }
            CardInfo cardInfo = cardInfoDao.selectByPrimaryKey(id);
            if(cardInfo == null){
                return Result.error("卡片信息不存在");
            }
            Result result = Result.ok("");
            result.put("cardInfo",cardInfo);
            return result;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result selectBookInfoById(Long id) {
        try {
            if(id == null || id <= 0){
                return Result.error("信息错误");
            }
            Book book = bookDao.selectByPrimaryKey(id);
            if(book == null){
                return Result.error("信息不存在");
            }
            Result result = Result.ok();
            result.put("book",book);
            return result;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result saveBorrowRecord(BorrowRecord borrowRecord,String type) {
        try {
            CardInfo cardInfo = cardInfoDao.selectByPrimaryKey(borrowRecord.getCardId());
            if(cardInfo == null){
                return Result.error("信息错误");
            }
            if(cardInfo.getBorrowNum().intValue() <= 0){
                return Result.error("借书已达上线");
            }
            String eventType = BookEnum.borrow.getCode();
            Integer borrowNum = 0;
            if(type.equals(BookEnum.give_back.getCode())){
                eventType = BookEnum.give_back.getCode();
                borrowNum = cardInfo.getBorrowNum() + 1;
            }else{
                borrowNum = cardInfo.getBorrowNum() - 1;
            }
            //添加借书记录
            borrowRecord.setEventType(eventType);
            borrowRecord.setOptionUser(ShiroUtils.getUser().getName());
            borrowRecordDao.insertSelective(borrowRecord);
            //改变借书额度
            cardInfo.setBorrowNum(borrowNum);
            cardInfoDao.updateByPrimaryKeySelective(cardInfo);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("操作失败");
    }
}
