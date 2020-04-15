package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.ClassesDao;
import com.zx.card.model.Classes;
import com.zx.card.model.ClassesExample;
import com.zx.card.service.IClassesService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements IClassesService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassesDao classesDao;

    @Override
    public Result selectClassesByPage(Page<Classes> pageInfo, Classes classes) {
        try {
            ClassesExample example = new ClassesExample();
            ClassesExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(classes.getName())){
                criteria.andNameEqualTo(classes.getName());
            }
            if(classes.getFacultyId() != null){
                criteria.andFacultyIdEqualTo(classes.getFacultyId());
            }
            if(classes.getSpecialtyId() != null){
                criteria.andSpecialtyIdEqualTo(classes.getSpecialtyId());
            }
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<Classes> list = classesDao.selectByExample(example);
            PageInfo<Classes> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveClasses(Classes classes) {
        try {
            if(classes.getFacultyId() == null){
                return Result.error("请选择院系");
            }
            if(classes.getSpecialtyId() == null){
                return Result.error("请选择专业");
            }
            //查询是否存在
            ClassesExample example = new ClassesExample();
            example.createCriteria().andSpecialtyIdEqualTo(classes.getFacultyId())
                    .andFacultyIdEqualTo(classes.getFacultyId())
                    .andNameEqualTo(classes.getName());
            List<Classes> classes1 = classesDao.selectByExample(example);
            if(classes1 != null && classes1.size() > 0){
                return Result.error("该院系专业已存在此班级");
            }
            classesDao.insertSelective(classes);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Classes selectClasses(Long id) {
        try {
            return classesDao.selectByPrimaryKey(id);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateClasses(Classes classes) {
        try {
            if(classes.getFacultyId() == null){
                return Result.error("请选择院系");
            }
            if(classes.getSpecialtyId() == null){
                return Result.error("请选择专业");
            }
            //查询是否存在
            ClassesExample example = new ClassesExample();
            example.createCriteria().andSpecialtyIdEqualTo(classes.getSpecialtyId())
                    .andFacultyIdEqualTo(classes.getFacultyId())
                    .andNameEqualTo(classes.getName());
            List<Classes> classes1 = classesDao.selectByExample(example);
            if(classes1 != null && classes1.size() > 0) {
                return Result.error("该院系专业已存在此班级");
            }
            classesDao.updateByPrimaryKeySelective(classes);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
