package com.zx.card.system.service;

import com.github.pagehelper.Page;
import com.zx.card.system.model.Dict;
import com.zx.card.utils.Result;

public interface DictService {

    Result selectDictByPage(Page<Dict> pageInfo, Dict dict);

    Dict selectDictByID(Long id);

    Result saveDict(Dict dict);

    Result updateDict(Dict dict);

    Result removeDict(Long id);

}
