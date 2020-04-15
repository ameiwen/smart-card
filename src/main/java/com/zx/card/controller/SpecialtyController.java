package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.enums.DeptEnum;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.service.IFacultySpecialtyService;
import com.zx.card.service.ITeacherStudentService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/info/specialty")
public class SpecialtyController {

    @Autowired
    private IFacultySpecialtyService facultySpecialtyService;
    @Autowired
    private ITeacherStudentService teacherStudentService;


    @GetMapping
    public String specialty() {
        return  "info/specialty/specialty";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<FacultySpecialty> page, FacultySpecialty facultySpecialty) {
        //指定院系
        facultySpecialty.setType(DeptEnum.specialty.getCode());
        return facultySpecialtyService.selectFacultySpecialtyByPage(page,facultySpecialty);
    }

    @GetMapping(value = "/add")
    public String add(HttpServletRequest request) {
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),0l);
        request.setAttribute("faculties",faculties);
        return "info/specialty/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(FacultySpecialty facultySpecialty) {
        facultySpecialty.setType(DeptEnum.specialty.getCode());
        return facultySpecialtyService.saveFacultySpecialty(facultySpecialty);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        List<FacultySpecialty> faculties = teacherStudentService.selectFacultySpecialtyByType(DeptEnum.faculty.getCode(),id);
        FacultySpecialty facultySpecialty = facultySpecialtyService.selectFacultySpecialty(id,DeptEnum.specialty.getCode());
        request.setAttribute("specialty",facultySpecialty);
        request.setAttribute("faculties",faculties);
        return "info/specialty/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(FacultySpecialty facultySpecialty){
        facultySpecialty.setType(DeptEnum.specialty.getCode());
        return facultySpecialtyService.updateFacultySpecialty(facultySpecialty);
    }

}
