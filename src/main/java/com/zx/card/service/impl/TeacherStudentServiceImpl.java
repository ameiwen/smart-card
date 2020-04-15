package com.zx.card.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.ClassesDao;
import com.zx.card.dao.FacultySpecialtyDao;
import com.zx.card.dao.TeacherStudentDao;
import com.zx.card.enums.DeptEnum;
import com.zx.card.enums.StatusEnum;
import com.zx.card.model.*;
import com.zx.card.service.ITeacherStudentService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TeacherStudentServiceImpl implements ITeacherStudentService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private TeacherStudentDao teacherStudentDao;
    @Autowired
    private FacultySpecialtyDao facultySpecialtyDao;


    @Override
    public Result selectStudentByPage(Page<TeacherStudent> pageInfo, TeacherStudent teacherStudent) {
        try {
            TeacherStudentExample example = new TeacherStudentExample();
            TeacherStudentExample.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotBlank(teacherStudent.getName())){
                criteria.andNameEqualTo(teacherStudent.getName());
            }
            if(teacherStudent.getId() != null){
                criteria.andIdEqualTo(teacherStudent.getId());
            }
            criteria.andRoleEqualTo(teacherStudent.getRole());
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<TeacherStudent> list = teacherStudentDao.selectByExample(example);
            list.forEach(stu ->{
                FacultySpecialty faculty = facultySpecialtyDao.selectByPrimaryKey(stu.getFacultyId());
                stu.setFaculty(faculty);
                FacultySpecialty specialty = facultySpecialtyDao.selectByPrimaryKey(stu.getSpecialtyId());
                stu.setSpecialty(specialty);
            });
            PageInfo<TeacherStudent> infos = new PageInfo<>(list);
            return Result.ok("",infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result removeTeacherStudent(Long id) {
        try {
            TeacherStudent teacherStudent = new TeacherStudent();
            teacherStudent.setStatus(StatusEnum.is_delete.getCode());
            teacherStudent.setId(id);
            int count = teacherStudentDao.updateByPrimaryKeySelective(teacherStudent);
            if(count > 0){
                return Result.ok("操作成功");
            }
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public List<FacultySpecialty> selectFacultySpecialty(String type) {
        try {
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            example.createCriteria().andTypeEqualTo(type);
            List<FacultySpecialty> list = facultySpecialtyDao.selectByExample(example);
            return list;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result addStudentSpecialtyInit(Long facultyId) {
        try {
            if(facultyId == null && facultyId.intValue() <= 0){
                return Result.error("系统错误");
            }
            FacultySpecialtyExample example = new FacultySpecialtyExample();
            example.createCriteria().andParentIdEqualTo(facultyId).andTypeEqualTo(DeptEnum.specialty.getCode());
            List<FacultySpecialty> list = facultySpecialtyDao.selectByExample(example);
            return Result.ok("",list);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result addStudentClassesInit(Map<String, String> param) {
        try {
            Long facultyId = NumberUtil.parseLong(param.get("facultyId"));
            Long specialtyId = NumberUtil.parseLong(param.get("specialtyId"));
            if(facultyId == null || specialtyId == null){
                return Result.error("参数错误");
            }
            ClassesExample example = new ClassesExample();
            example.createCriteria().andFacultyIdEqualTo(facultyId).andSpecialtyIdEqualTo(specialtyId);
            List<Classes> classes = classesDao.selectByExample(example);
            return Result.ok("",classes);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveTeacherStudent(TeacherStudent teacherStudent) {
        try {
            //判断身份证是否合法
            if(!IdcardUtil.isValidCard(teacherStudent.getIdCard())){
                return Result.error("身份证不合法");
            }
            //判断信息是否存在
            TeacherStudentExample example = new TeacherStudentExample();
            example.createCriteria().andIdCardEqualTo(teacherStudent.getIdCard());
            List<TeacherStudent> exits = teacherStudentDao.selectByExample(example);
            if(exits.size() > 0){
                return Result.error("当前身份证已被使用");
            }
            example.createCriteria().andPhoneEqualTo(teacherStudent.getPhone());
            exits = teacherStudentDao.selectByExample(example);
            if(exits.size() > 0){
                return Result.error("当前手机号已被使用");
            }
            //查询班级
            Classes classes = classesDao.selectByPrimaryKey(teacherStudent.getClassId());
            teacherStudent.setClassName(classes.getName());
            //获取出生年月
            String birthday = IdcardUtil.getBirth(teacherStudent.getIdCard());
            teacherStudent.setBirthday(birthday);
            int count = teacherStudentDao.insertSelective(teacherStudent);
            if(count > 0){
                return Result.ok("操作成功");
            }
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public TeacherStudent selectTeacherStudentById(Long id) {
        try {
            if(id == null || id.intValue() <= 0){
                return null;
            }
            TeacherStudent teacherStudent = teacherStudentDao.selectByPrimaryKey(id);
            if(teacherStudent == null){
                return null;
            }
            return teacherStudent;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public List<FacultySpecialty> selectFacultySpecialtyByType(String type, Long id) {
        try {
            if(DeptEnum.faculty.getCode().equals(type)){
                FacultySpecialtyExample example = new FacultySpecialtyExample();
                example.createCriteria().andTypeEqualTo(DeptEnum.faculty.getCode());
                List<FacultySpecialty> faculties = facultySpecialtyDao.selectByExample(example);
                return faculties;
            }else if(DeptEnum.specialty.getCode().equals(type)){
                //专业信息
                if(id == null || id.intValue() <= 0){
                    return null;
                }
                TeacherStudent teacherStudent = teacherStudentDao.selectByPrimaryKey(id);
                if(teacherStudent == null){
                    return null;
                }
                FacultySpecialtyExample example = new FacultySpecialtyExample();
                example.createCriteria().andTypeEqualTo(DeptEnum.specialty.getCode())
                        .andIdEqualTo(teacherStudent.getSpecialtyId());
                List<FacultySpecialty> specialties = facultySpecialtyDao.selectByExample(example);
                return specialties;
            }
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public List<Classes> selectClassesByID(Long id) {
        try {
            TeacherStudent teacherStudent = teacherStudentDao.selectByPrimaryKey(id);
            if(teacherStudent == null){
                return null;
            }
            ClassesExample example = new ClassesExample();
            example.createCriteria().andSpecialtyIdEqualTo(teacherStudent.getSpecialtyId())
                    .andFacultyIdEqualTo(teacherStudent.getFacultyId());
            List<Classes> classes = classesDao.selectByExample(example);
            return classes;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateStudent(TeacherStudent teacherStudent) {
        try {
            if(teacherStudent.getId() == null){
                return Result.error("参数错误");
            }
            TeacherStudent teacherStudent1 = teacherStudentDao.selectByPrimaryKey(teacherStudent.getId());
            if(teacherStudent1 == null){
                return Result.error("参数错误");
            }
            //判断身份证是否合法
            if(!IdcardUtil.isValidCard(teacherStudent.getIdCard())){
                return Result.error("身份证不合法");
            }
            //判断信息是否存在
            TeacherStudentExample example = new TeacherStudentExample();
            example.createCriteria().andIdCardEqualTo(teacherStudent.getIdCard())
                    .andIdCardNotEqualTo(teacherStudent1.getIdCard());
            List<TeacherStudent> exits = teacherStudentDao.selectByExample(example);
            if(exits.size() > 0){
                return Result.error("当前身份证已被使用");
            }
            example.createCriteria().andPhoneEqualTo(teacherStudent.getPhone())
                    .andPhoneNotEqualTo(teacherStudent1.getPhone());
            exits = teacherStudentDao.selectByExample(example);
            if(exits.size() > 0){
                return Result.error("当前手机号已被使用");
            }
            //获取出生年月
            String birthday = IdcardUtil.getBirth(teacherStudent.getIdCard());
            teacherStudent.setBirthday(birthday);
            teacherStudentDao.updateByPrimaryKeySelective(teacherStudent);
            return Result.ok("操作成功");
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}
