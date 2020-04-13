package com.zx.card.system.dao;

import com.zx.card.system.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMenuMapper {

    int deleteRoleMenuByPrimaryKey(Integer id);

    int insertRoleMenuSelective(RoleMenu record);

    RoleMenu selectRoleMenuByPrimaryKey(Integer id);

    int updateRoleMenuByPrimaryKeySelective(RoleMenu record);

    int removeRoleMenuByRoleId(Integer roleId);

    int batchSaveRoleMenu(List<RoleMenu> roleMenus);

    List<Integer> listMenuIdByRoleId(Integer roleId);

}