package com.zx.card.system.service.impl;

import com.zx.card.system.dao.GenMapper;
import com.zx.card.system.service.GenService;
import com.zx.card.utils.GenUtils;
import com.zx.card.utils.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
public class GenServiceImpl implements GenService {

    @Autowired
    private GenMapper genMapper;

    @Override
    public Result list() {
        List<Map<String, Object>> list = genMapper.list();
        Result result = Result.ok("操作成功");
        result.put("data",list);
        return result;
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(String tableName : tableNames){
            //查询表信息
            Map<String, String> table = genMapper.get(tableName);
            //查询列信息
            List<Map<String, String>> columns = genMapper.listColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
