package com.zx.card.system.dao;

import com.zx.card.system.model.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DictMapper {

    int deleteDictByPrimaryKey(Long id);

    int insertDictSelective(Dict record);

    Dict selectDictByPrimaryKey(Long id);

    int updateDictByPrimaryKeySelective(Dict record);

    List<Dict> selectDictListWhere(Map<String, Object> param);

}