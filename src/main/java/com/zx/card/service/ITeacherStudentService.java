package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Classes;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.model.TeacherStudent;
import com.zx.card.utils.Result;

import java.util.List;
import java.util.Map;

public interface ITeacherStudentService {

    Result selectStudentByPage(Page<TeacherStudent> pageInfo, TeacherStudent teacherStudent);

    Result removeTeacherStudent(Long id);

    List<FacultySpecialty> selectFacultySpecialty(String type);

    Result addStudentSpecialtyInit(Long facultyId);

    Result addStudentClassesInit(Map<String,String> param);

    Result saveTeacherStudent(TeacherStudent teacherStudent);

    TeacherStudent selectTeacherStudentById(Long ID);

    List<FacultySpecialty> selectFacultySpecialtyByType(String type,Long id);

    List<Classes> selectClassesByID(Long id);

    Result updateStudent(TeacherStudent teacherStudent);

}
