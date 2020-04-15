package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.FacultySpecialty;
import com.zx.card.utils.Result;

public interface IFacultySpecialtyService {

    Result selectFacultySpecialtyByPage(Page<FacultySpecialty> pageInfo, FacultySpecialty facultySpecialty);

    Result saveFacultySpecialty(FacultySpecialty facultySpecialty);

    FacultySpecialty selectFacultySpecialty(Long id,String type);

    Result updateFacultySpecialty(FacultySpecialty facultySpecialty);

}
