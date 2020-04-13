package com.zx.card.system.dao;

import com.zx.card.system.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper {

    int deleteRoleByPrimaryKey(Integer id);

    int insertRoleSelective(Role record);

    Role selectRoleByPrimaryKey(Integer id);

    int updateRoleByPrimaryKeySelective(Role record);

    List<Role> selectRoleListWhere(Map<String, Object> param);

}