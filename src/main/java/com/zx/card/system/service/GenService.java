package com.zx.card.system.service;


import com.zx.card.utils.Result;

public interface GenService {

    Result list();

    byte[] generatorCode(String[] tableNames);

}
