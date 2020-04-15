package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Canteen;
import com.zx.card.utils.Result;

public interface ICanteenService {

    Result selectCanteenByPage(Page<Canteen> page, Canteen canteen);

    Result saveCanteen(Canteen canteen);

    Canteen selectCanteen(Long id);

    Result updateCanteen(Canteen canteen);

}
