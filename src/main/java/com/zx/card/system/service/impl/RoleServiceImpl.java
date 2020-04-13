package com.zx.card.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.system.dao.RoleMapper;
import com.zx.card.system.dao.RoleMenuMapper;
import com.zx.card.system.dao.UserRoleMapper;
import com.zx.card.system.model.Role;
import com.zx.card.system.model.RoleMenu;
import com.zx.card.system.service.RoleService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> selectRoleList() {
        List<Role> list = roleMapper.selectRoleListWhere(new HashMap<>());
        return list;
    }

    @Override
    public List<Role> selectRoleByUserID(Integer userID) {
        List<Integer> rolesIds = userRoleMapper.selectRoleIdList(userID);
        List<Role> roles = roleMapper.selectRoleListWhere(new HashMap<>());
        for (Role role : roles) {
            role.setStatus("false");
            for (Integer roleId : rolesIds) {
                if (Objects.equals(role.getId(), roleId)) {
                    role.setStatus("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public Result selectRoleByPage(Page<Role> pageInfo, Role role) {
        Map<String,Object> search = new HashMap<>();
        if(StringUtils.isNotBlank(role.getName())){
            search.put("name",role.getName());
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Role> roles = roleMapper.selectRoleListWhere(search);
        PageInfo<Role> pageInfos = new PageInfo<Role>(roles);
        Result result = Result.ok("操作成功");
        result.put("data",pageInfos);
        return result;
    }

    @Override
    public Role selectRoleByID(Integer id) {
        return roleMapper.selectRoleByPrimaryKey(id);
    }

    @Override
    public int saveRole(Role role) {
        int r = roleMapper.insertRoleSelective(role);
        List<Integer> menuIds = role.getMenuIds();
        List<RoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(role.getId());
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSaveRoleMenu(rms);
        }
        return r;
    }

    @Override
    public int updateRole(Role role) {
        int r = roleMapper.updateRoleByPrimaryKeySelective(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getId();
        roleMenuMapper.removeRoleMenuByRoleId(roleId);
        List<RoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSaveRoleMenu(rms);
        }
        return r;
    }

    @Override
    public int removeRole(Integer id) {
        return roleMapper.deleteRoleByPrimaryKey(id);
    }
}
