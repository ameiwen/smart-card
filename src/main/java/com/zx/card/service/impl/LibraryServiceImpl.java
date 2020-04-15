package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.LibraryDao;
import com.zx.card.model.Library;
import com.zx.card.model.LibraryExample;
import com.zx.card.service.ILibraryService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements ILibraryService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LibraryDao libraryDao;

    @Override
    public Result selectLibraryByPage(Page<Library> page, Library library) {
        try {
            LibraryExample example = new LibraryExample();
            LibraryExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(library.getLeader())) {
                criteria.andLeaderEqualTo(library.getLeader());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<Library> list = libraryDao.selectByExample(example);
            PageInfo<Library> infos = new PageInfo<>(list);
            return Result.ok("", infos);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveLibrary(Library library) {
        try {
            if (StringUtils.isBlank(library.getLeader())) {
                return Result.error("领导人不能为空");
            }
            libraryDao.insertSelective(library);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Library selectLibrary(Long id) {
        try {
            if (id == null || id <= 0) {
                return null;
            }
            return libraryDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateLibrary(Library library) {
        try {
            if (library.getId() == null || library.getId() <= 0) {
                return Result.error("参数错误");
            }
            libraryDao.updateByPrimaryKeySelective(library);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

}
