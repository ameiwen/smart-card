package com.zx.card.system.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.system.model.Role;
import com.zx.card.system.model.User;
import com.zx.card.system.service.RoleService;
import com.zx.card.system.service.UserService;
import com.zx.card.utils.AlgorithmUtil;
import com.zx.card.utils.Result;
import com.zx.card.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private String prefix="system/user/";

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    @RequiresPermissions("sys:user:user")
    public String user() {
        return  "system/user/user";
    }

    /**
     * 用户列表
     * @param page
     * @param user
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("sys:user:user")
    public Result list(Page<User> page, User user) {
        return userService.selectUserByPage(page,user);
    }

    /**
     * 新增用户页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("sys:user:add")
    public String add(HttpServletRequest request) {
        List<Role> roles = roleService.selectRoleList();
        request.setAttribute("roles", roles);
        return prefix + "add";
    }

    /**
     * 修改用户页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:user:edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        User user = userService.selectUserByID(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.selectRoleByUserID(id);
        model.addAttribute("roles", roles);
        return prefix+"edit";
    }

    /**
     * 修改密码页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/resetPwd/{id}")
    @RequiresPermissions("sys:user:resetPwd")
    public String resetPwd(@PathVariable("id") Integer id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user", user);
        return prefix + "reset";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("sys:user:edit")
    public Result update(User user) {
        if (userService.updateUserByRecord(user) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    /**
     * 新建用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:user:add")
    public Result save(User user) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        AlgorithmUtil.encrypt(user);
        if (userService.saveUser(user) > 0) {
            return Result.ok("操作成功");
        }
        return Result.error();
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/resetPwd")
    public Result resetPwd(User user) {
        return userService.resetPassword(user, ShiroUtils.getUser());
    }

    /**
     * 超级管理员修改密码
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/adminResetPwd")
    @RequiresPermissions("sys:user:resetPwd")
    public Result adminResetPwd(User user) {
        return userService.adminResetPwd(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("sys:user:remove")
    public Result remove(Integer id) {
        if (userService.removeUserByID(id) > 0) {
            return Result.ok("操作成功");
        }
        return Result.error();
    }

    /**
     * 用户资料页面
     */
    @GetMapping(value = "/profile")
    public String getProfile(Model model){
        User user = ShiroUtils.getUser();
        model.addAttribute("user",user);
        return prefix + "info";
    }

    /**
     * 修改密码页面
     */
    @GetMapping(value = "/password")
    public String updatePassword(Model model){
        User user = ShiroUtils.getUser();
        model.addAttribute("user",user);
        return prefix + "password";
    }


    /**
     * 注销
     */
    @ResponseBody
    @PostMapping(value = "/loginout")
    public Result loginout(){
        ShiroUtils.logout();
        return Result.ok("");
    }



}
