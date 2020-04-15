package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.enums.DeptEnum;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.service.IFacultySpecialtyService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/info/faculty")
public class FacultyController {

    @Autowired
    private IFacultySpecialtyService facultySpecialtyService;

    @GetMapping
    public String faculty() {
        return  "info/faculty/faculty";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<FacultySpecialty> page, FacultySpecialty facultySpecialty) {
        //指定院系
        facultySpecialty.setType(DeptEnum.faculty.getCode());
        return facultySpecialtyService.selectFacultySpecialtyByPage(page,facultySpecialty);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "info/faculty/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(FacultySpecialty facultySpecialty) {
        facultySpecialty.setType(DeptEnum.faculty.getCode());
        return facultySpecialtyService.saveFacultySpecialty(facultySpecialty);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        FacultySpecialty facultySpecialty = facultySpecialtyService.selectFacultySpecialty(id,DeptEnum.faculty.getCode());
        request.setAttribute("faculty",facultySpecialty);
        return "info/faculty/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(FacultySpecialty facultySpecialty){
        facultySpecialty.setType(DeptEnum.faculty.getCode());
        return facultySpecialtyService.updateFacultySpecialty(facultySpecialty);
    }

}
