package com.zx.card.system.service;

import com.github.pagehelper.Page;
import com.zx.card.system.model.Role;
import com.zx.card.utils.Result;

import java.util.List;

public interface RoleService {

    List<Role> selectRoleList();

    List<Role> selectRoleByUserID(Integer userID);

    Result selectRoleByPage(Page<Role> pageInfo, Role role);

    Role selectRoleByID(Integer id);

    int saveRole(Role role);

    int updateRole(Role role);

    int removeRole(Integer id);

}
