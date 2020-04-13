package com.zx.card.system.dao;

import com.zx.card.system.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserRoleMapper {

    int deleteUserRoleByPrimaryKey(Integer id);

    int insertUserRoleSelective(UserRole record);

    UserRole selectUserRoleByPrimaryKey(Integer id);

    int updateUserRoleByPrimaryKeySelective(UserRole record);

    List<UserRole> selectUserRoleListWhere(Map<String, Object> params);

    List<Integer> selectRoleIdList(Integer userId);

    List<Integer> listRoleId(Integer userId);

    int removeByUserId(Integer userId);

    int batchSave(List<UserRole> list);


}