package com.zx.card.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.system.dao.DictMapper;
import com.zx.card.system.model.Dict;
import com.zx.card.system.service.DictService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Result selectDictByPage(Page<Dict> pageInfo, Dict dict) {
        Map<String,Object> search = new HashMap<>();
        if(StringUtils.isNotBlank(dict.getName())){
            search.put("name",dict.getName());
        }
        if(StringUtils.isNotBlank(dict.getType())){
            search.put("type",dict.getType());
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Dict> list = dictMapper.selectDictListWhere(search);
        PageInfo<Dict> infos = new PageInfo<Dict>(list);
        Result result = Result.ok("操作成功");
        result.put("data",infos);
        return result;
    }

    @Override
    public Dict selectDictByID(Long id) {
       return dictMapper.selectDictByPrimaryKey(id);
    }

    @Override
    public Result saveDict(Dict dict) {
        if(dictMapper.insertDictSelective(dict) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result updateDict(Dict dict) {
        if(dictMapper.updateDictByPrimaryKeySelective(dict) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result removeDict(Long id) {
        if(dictMapper.deleteDictByPrimaryKey(id)>0) {
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

}
