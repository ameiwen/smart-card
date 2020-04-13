package com.zx.card.system.dao;

import com.zx.card.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    int deleteUserByPrimaryKey(Integer id);

    int insertUserSelective(User record);

    User selectUserByPrimaryKey(Integer id);

    int updateUserByPrimaryKeySelective(User record);

    List<User> selectUserByWhere(Map<String, Object> params);

}