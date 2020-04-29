package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.enums.RoleEnum;
import com.zx.card.model.TeacherStudent;
import com.zx.card.service.ITeacherStudentService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/info/teacher")
public class TeacherController {

    @Autowired
    private ITeacherStudentService teacherStudentService;

    @GetMapping
    public String student() {
        return  "/info/teacher/teacher";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<TeacherStudent> page, TeacherStudent teacherStudent) {
        //指定老师
        teacherStudent.setRole(RoleEnum.teacher.getCode());
        return teacherStudentService.selectStudentByPage(page,teacherStudent);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "info/teacher/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(TeacherStudent teacherStudent) {
        teacherStudent.setRole(RoleEnum.teacher.getCode());
        return teacherStudentService.saveTeacherStudent(teacherStudent);
    }

    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(Long id) {
        return teacherStudentService.removeTeacherStudent(id);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request){
        //老师信息
        TeacherStudent teacherStudent = teacherStudentService.selectTeacherStudentById(id);
        request.setAttribute("teacher",teacherStudent);
        return "info/teacher/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(TeacherStudent teacherStudent){
        return teacherStudentService.updateStudent(teacherStudent);
    }

}
