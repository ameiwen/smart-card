package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.enums.DeptEnum;
import com.zx.card.model.Classes;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.service.IClassesService;
import com.zx.card.service.ITeacherStudentService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/info/classes")
public class ClassesController {

    @Autowired
    private IClassesService classesService;
    @Autowired
    private ITeacherStudentService teacherStudentService;

    @GetMapping
    public String classes(HttpServletRequest request) {
        //院系信息查询
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),0l);
        request.setAttribute("faculties",faculties);
        return "info/classes/classes";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<Classes> page, Classes classes) {
        return classesService.selectClassesByPage(page,classes);
    }

    @GetMapping(value = "/add")
    public String add(HttpServletRequest request) {
        //院系信息查询
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),0l);
        request.setAttribute("faculties",faculties);
        return "info/classes/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(Classes classes) {
        return classesService.saveClasses(classes);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Classes classes = classesService.selectClasses(id);
        //院系信息查询
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),0l);
        //专业查询
        List<FacultySpecialty> specialties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.specialty.getCode(),classes.getFacultyId());
        request.setAttribute("faculties",faculties);
        request.setAttribute("specialties",specialties);
        request.setAttribute("classes",classes);
        return "info/classes/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(Classes classes){
        return classesService.updateClasses(classes);
    }


}
