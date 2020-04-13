package com.zx.card.system.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.system.model.Role;
import com.zx.card.system.service.RoleService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @RequiresPermissions("sys:role:role")
    public String menu() {
        return  "system/role/role";
    }

    /**
     * 角色列表
     * @param page
     * @param role
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("sys:user:user")
    public Result list(Page<Role> page, Role role) {
        return roleService.selectRoleByPage(page,role);
    }


    /**
     * 添加页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("sys:role:add")
    public String add() {
        return "system/role/add";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:role:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.selectRoleByID(id);
        model.addAttribute("role", role);
        return "system/role/edit";
    }

    /**
     * 保存
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:role:add")
    public Result save(Role role) {
        if (roleService.saveRole(role) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    /**
     * 修改
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("sys:role:edit")
    public Result update(Role role) {
        if (roleService.updateRole(role) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("sys:role:remove")
    public Result save(Integer id) {
        if (roleService.removeRole(id) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "删除失败");
        }
    }


}
