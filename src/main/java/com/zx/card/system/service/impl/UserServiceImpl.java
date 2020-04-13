package com.zx.card.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.system.dao.RoleMapper;
import com.zx.card.system.dao.RoleMenuMapper;
import com.zx.card.system.dao.UserMapper;
import com.zx.card.system.dao.UserRoleMapper;
import com.zx.card.system.model.Role;
import com.zx.card.system.model.User;
import com.zx.card.system.model.UserRole;
import com.zx.card.system.service.UserService;
import com.zx.card.utils.AlgorithmUtil;
import com.zx.card.utils.Result;
import com.zx.card.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public User selectUserByUsername(String username) {
        try {
            Map<String,Object> search = new HashMap<>();
            search.put("username",username);
            List<User> user = userMapper.selectUserByWhere(search);
        if(user != null){
            return user.get(0);
        }

        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result selectUserByPage(Page<User> page, User user) {
        try {
            Map<String,Object> search = new HashMap<>();
            if(StringUtils.isNotBlank(user.getName())){
                search.put("name",user.getName());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<User> users = userMapper.selectUserByWhere(search);
            for (User u : users) {
                List<Integer> roleIds = userRoleMapper.selectRoleIdList(u.getId());
                if (roleIds != null && roleIds.size() > 0) {
                    String roleNames = null;
                    for (Integer roleId : roleIds) {
                        Role role = roleMapper.selectRoleByPrimaryKey(roleId);
                        if(roleNames==null){
                            roleNames = role.getName();
                        }else{
                            roleNames = roleNames + "," + role.getName();
                        }
                    }
                    u.setRoleNames(roleNames);
                }
                u.setRoleIds(roleIds);
            }
            PageInfo<User> pageInfo = new PageInfo<User>(users);
            Result result = Result.ok("操作成功");
            result.put("data",pageInfo);
            return result;
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public User selectUserByID(Integer id) {
        List<Integer> roleIds = userRoleMapper.listRoleId(id);
        User user = userMapper.selectUserByPrimaryKey(id);
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public int removeUserByID(Integer id) {
        User user = userMapper.selectUserByPrimaryKey(id);
        //删除菜单
        List<Integer> roleIds = user.getRoleIds();
        if(roleIds != null && roleIds.size() > 0){
            for(Integer roleId : roleIds){
                roleMenuMapper.removeRoleMenuByRoleId(roleId);
            }
        }
        //删除角色
        userRoleMapper.removeByUserId(id);
        //删除用户
        return userMapper.deleteUserByPrimaryKey(id);
    }

    @Override
    public int updateUserByRecord(User user) {
        int r = userMapper.updateUserByPrimaryKeySelective(user);
        Integer userId = user.getId();
        List<Integer> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            if(roleId != null) {
                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return r;
    }

    @Override
    public int saveUser(User user) {
        int count = userMapper.insertUserSelective(user);
        Integer userId = user.getId();
        List<Integer> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            if(roleId != null) {
                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public Result resetPassword(User user, User loginUser) {
        if (Objects.equals(user.getId(), loginUser.getId())) {
            if (Objects.equals(AlgorithmUtil.encrypt(user.getPassword(), loginUser.getSalt()), loginUser.getPassword())) {
                user.setPassword(AlgorithmUtil.encrypt(user.getPassword(), loginUser.getSalt()));
                int i = userMapper.updateUserByPrimaryKeySelective(user);
                if(i>0){
                    return Result.ok("操作成功");
                }
            }else{
                return Result.error("输入的旧密码有误");
            }
        }
        return Result.error("你修改的不是你登录的账号");
    }

    @Override
    public Result adminResetPwd(User user) {
        User loginUser = ShiroUtils.getUser();
        User updateUser = userMapper.selectUserByPrimaryKey(user.getId());
        //查询修改用户权限
        Map<String,Object> search = new HashMap<>();
        search.put("userId",loginUser.getId());
        List<UserRole> updateRoles = userRoleMapper.selectUserRoleListWhere(search);
        boolean perms = false;
        for(UserRole role : updateRoles){
            //超级管理员可以修改
            if(role.getRoleId() == 1){
                perms = true;
            }
        }
        if(perms == false){
            return Result.error("当前权限不能修改密码");
        }
        //默认密码(未输入需要修改的密码)
        if(user.getPassword().equals("111111")){
            return Result.ok("操作成功");
        }
        updateUser.setPassword(AlgorithmUtil.encrypt(user.getPassword(), updateUser.getSalt()));
        int i = userMapper.updateUserByPrimaryKeySelective(updateUser);
        if(i > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

}
