package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.enums.DeptEnum;
import com.zx.card.enums.RoleEnum;
import com.zx.card.model.Classes;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.model.TeacherStudent;
import com.zx.card.service.ITeacherStudentService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/info/student")
public class StudentController extends BaseController {

    @Autowired
    private ITeacherStudentService teacherStudentService;

    @GetMapping
    @RequiresPermissions(value = "info:student:student")
    public String order() {
        return  "info/student/student";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("info:student:student")
    public Result list(Page<TeacherStudent> page, TeacherStudent teacherStudent) {
        //指定学生
        teacherStudent.setRole(RoleEnum.student.getCode());
        return teacherStudentService.selectStudentByPage(page,teacherStudent);
    }

    @GetMapping(value = "/add")
    @RequiresPermissions("info:student:add")
    public String add(HttpServletRequest request) {
        //院系初始化信息
        List<FacultySpecialty> faculty = teacherStudentService.selectFacultySpecialty(DeptEnum.faculty.getCode());
        request.setAttribute("faculty",faculty);
        return "info/student/add";
    }

    @ResponseBody
    @RequiresPermissions("info:student:add")
    @PostMapping(value = "/addStudentSpecialtyInit/{id}")
    public Result addStudentSpecialtyInit(@PathVariable("id") Long id){
        //专业初始化信息
        return teacherStudentService.addStudentSpecialtyInit(id);
    }

    @ResponseBody
    @RequiresPermissions("info:student:add")
    @PostMapping(value = "/addStudentClassesInit")
    public Result addStudentClassesInit(HttpServletRequest request){
        //班级初始化信息
        Map<String,String> param = buildParam(request);
        return teacherStudentService.addStudentClassesInit(param);
    }

    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("info:student:add")
    public Result save(TeacherStudent teacherStudent) {
        return teacherStudentService.saveTeacherStudent(teacherStudent);
    }

    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("info:student:add")
    public Result remove(Long id) {
        return teacherStudentService.removeTeacherStudent(id);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request){
        //院系专业信息（回显）
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),id);
        List<FacultySpecialty> specialties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.specialty.getCode(),id);
        //班级信息
        List<Classes> classes = teacherStudentService.selectClassesByID(id);
        //学生信息
        TeacherStudent teacherStudent = teacherStudentService.selectTeacherStudentById(id);
        request.setAttribute("faculties",faculties);
        request.setAttribute("specialties",specialties);
        request.setAttribute("classes",classes);
        request.setAttribute("student",teacherStudent);
        return "info/student/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(TeacherStudent teacherStudent){
        return teacherStudentService.updateStudent(teacherStudent);
    }


}
