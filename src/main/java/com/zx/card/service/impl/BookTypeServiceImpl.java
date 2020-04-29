package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.BookTypeDao;
import com.zx.card.model.BookType;
import com.zx.card.model.BookTypeExample;
import com.zx.card.service.IBookTypeService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements IBookTypeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public Result selectBookTypeByPage(Page<BookType> pageInfo, BookType bookType) {
        try {
            BookTypeExample example = new BookTypeExample();
            BookTypeExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(bookType.getTypeName())){
                criteria.andTypeNameEqualTo(bookType.getTypeName());
            }
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<BookType> list = bookTypeDao.selectByExample(example);
            PageInfo<BookType> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveBookType(BookType bookType) {
        try {
            if(StringUtils.isBlank(bookType.getTypeName())){
                return Result.error("类型不能为空");
            }
            BookTypeExample example = new BookTypeExample();
            example.createCriteria().andTypeNameEqualTo(bookType.getTypeName());
            List<BookType> bookTypes = bookTypeDao.selectByExample(example);
            if(bookTypes != null && bookTypes.size() > 0){
                return Result.error("该院系已存在");
            }
            bookTypeDao.insertSelective(bookType);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public BookType selectBookType(Long id) {
        try {
            if(id == null || id <= 0){
                return null;
            }
            return bookTypeDao.selectByPrimaryKey(id);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateBookType(BookType bookType) {
        try {
            BookType old = bookTypeDao.selectByPrimaryKey(bookType.getId());
            if(old == null){
                return Result.error("参数错误");
            }
            //校验
            BookTypeExample example = new BookTypeExample();
            example.createCriteria().andTypeNameEqualTo(bookType.getTypeName());
            List<BookType> valid = bookTypeDao.selectByExample(example);
            if(valid != null && valid.size() > 0){
                return Result.error("该类型已存在！");
            }
            bookTypeDao.updateByPrimaryKeySelective(bookType);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
