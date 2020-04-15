package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Supermarket;
import com.zx.card.utils.Result;

public interface ISupermarketService {

    Result selectSupermarketByPage(Page<Supermarket> page, Supermarket supermarket);

    Result saveSupermarket(Supermarket supermarket);

    Supermarket selectSupermarket(Long id);

    Result updateSupermarket(Supermarket supermarket);
    
}
