package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Classes;
import com.zx.card.utils.Result;

public interface IClassesService {

    Result selectClassesByPage(Page<Classes> pageInfo, Classes classes);

    Result saveClasses(Classes classes);

    Classes selectClasses(Long id);

    Result updateClasses(Classes classes);

}
