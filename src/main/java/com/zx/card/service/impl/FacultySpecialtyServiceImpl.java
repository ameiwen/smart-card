package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.FacultySpecialtyDao;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.model.FacultySpecialtyExample;
import com.zx.card.service.IFacultySpecialtyService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultySpecialtyServiceImpl implements IFacultySpecialtyService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FacultySpecialtyDao facultySpecialtyDao;

    @Override
    public Result selectFacultySpecialtyByPage(Page<FacultySpecialty> pageInfo, FacultySpecialty facultySpecialty) {
        try {
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            FacultySpecialtyExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(facultySpecialty.getName())){
                criteria.andNameEqualTo(facultySpecialty.getName());
            }
            criteria.andTypeEqualTo(facultySpecialty.getType());
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<FacultySpecialty> list = facultySpecialtyDao.selectByExample(example);
            PageInfo<FacultySpecialty> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveFacultySpecialty(FacultySpecialty facultySpecialty) {
        try {
            if(StringUtils.isBlank(facultySpecialty.getName())){
                return Result.error("院系名称不能为空");
            }
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            example.createCriteria().andNameEqualTo(facultySpecialty.getName());
            List<FacultySpecialty> facultySpecialties = facultySpecialtyDao.selectByExample(example);
            if(facultySpecialties != null && facultySpecialties.size() > 0){
                return Result.error("该院系已存在");
            }
            facultySpecialtyDao.insertSelective(facultySpecialty);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public FacultySpecialty selectFacultySpecialty(Long id,String type) {
        try {
            if(id == null || id <= 0){
                return null;
            }
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            example.createCriteria().andTypeEqualTo(type)
                    .andIdEqualTo(id);
            List<FacultySpecialty> facultySpecialties = facultySpecialtyDao.selectByExample(example);
            if(facultySpecialties != null && facultySpecialties.size() > 0){
                return facultySpecialties.get(0);
            }
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateFacultySpecialty(FacultySpecialty facultySpecialty) {
        try {
            FacultySpecialty old = facultySpecialtyDao.selectByPrimaryKey(facultySpecialty.getId());
            if(old == null){
                return Result.error("参数错误");
            }
            //校验
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            example.createCriteria().andNameEqualTo(facultySpecialty.getName())
                    .andTypeEqualTo(facultySpecialty.getType())
                    .andIdNotEqualTo(facultySpecialty.getId());

            List<FacultySpecialty> valid = facultySpecialtyDao.selectByExample(example);
            if(valid != null && valid.size() > 0){
                return Result.error("该院系已存在");
            }
            facultySpecialtyDao.updateByPrimaryKeySelective(facultySpecialty);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
