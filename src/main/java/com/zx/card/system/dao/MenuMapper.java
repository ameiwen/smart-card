package com.zx.card.system.dao;

import com.zx.card.system.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuMapper {

    int deleteMenuByPrimaryKey(Integer id);

    int insertMenuSelective(Menu record);

    Menu selectMenuByPrimaryKey(Integer id);

    int updateMenuByPrimaryKeySelective(Menu record);

    List<Menu> selectMenuListWhere(Map<String, Object> param);

    List<String> selectUsersPerms(Integer id);

    List<Menu> selectMenuTreeByID(Integer id);

}