package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.BookDao;
import com.zx.card.dao.BookTypeDao;
import com.zx.card.enums.StatusEnum;
import com.zx.card.model.Book;
import com.zx.card.model.BookExample;
import com.zx.card.model.BookType;
import com.zx.card.model.BookTypeExample;
import com.zx.card.service.IBookService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public Result selectBookByPage(Page<Book> pageInfo, Book book) {
        try {
            BookExample example = new BookExample();
            BookExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(book.getName())){
                criteria.andNameEqualTo(book.getName());
            }
            if(StringUtils.isNotBlank(book.getAuthor())){
                criteria.andAuthorEqualTo(book.getAuthor());
            }
            criteria.andStatusEqualTo(StatusEnum.no_delete.getCode());
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<Book> list = bookDao.selectByExample(example);
            PageInfo<Book> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public List<BookType> selectAllBookType() {
        try {
            List<BookType> bookTypes = bookTypeDao.selectByExample(new BookTypeExample());
            return bookTypes;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result saveBook(Book book) {
        try {
            if(StringUtils.isBlank(book.getName())){
                return Result.error("书籍名称不能为空");
            }
            if(book.getTypeId() == null){
                return Result.error("书籍类型不能为空");
            }
            BookType bookType = bookTypeDao.selectByPrimaryKey(book.getTypeId());
            if(bookType == null){
                return Result.error("书籍类型错误");
            }
            book.setTypeName(bookType.getTypeName());
            BookExample example = new BookExample();
            example.createCriteria().andNameEqualTo(book.getName())
                    .andStatusEqualTo(StatusEnum.no_delete.getCode());
            List<Book> books = bookDao.selectByExample(example);
            if(books != null && books.size() > 0){
                return Result.error("该书籍已存在");
            }
            bookDao.insertSelective(book);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Book selectBook(Long id) {
        try {
            if(id == null || id.intValue() <= 0){
                return null;
            }
            return bookDao.selectByPrimaryKey(id);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateBook(Book book) {
        try {
            BookExample example = new BookExample();
            example.createCriteria().andNameEqualTo(book.getName())
                    .andIdNotEqualTo(book.getId())
                    .andStatusEqualTo(StatusEnum.no_delete.getCode());
            List<Book> books = bookDao.selectByExample(example);
            if(books != null && books.size() > 0){
                return Result.error("该书籍名称已被使用！");
            }
            bookDao.updateByPrimaryKeySelective(book);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result removeBook(Long id) {
        try {
            Book book = bookDao.selectByPrimaryKey(id);
            if(book == null){
                return Result.error("该书籍不存在");
            }
            book.setStatus(StatusEnum.is_delete.getCode());
            bookDao.updateByPrimaryKeySelective(book);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
