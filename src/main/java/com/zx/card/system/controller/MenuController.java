package com.zx.card.system.controller;

import com.zx.card.controller.base.BaseController;
import com.zx.card.system.model.Menu;
import com.zx.card.system.model.vo.Tree;
import com.zx.card.system.service.MenuService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;


    @GetMapping
    @RequiresPermissions("sys:menu:menu")
    public String menu() {
        return  "system/menu/menu";
    }


    /**
     * 菜单列表
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("sys:menu:menu")
    public Result list() {
        List<Menu> menus = menuService.selectAllMenu();
        Result result = Result.ok();
        result.put("data",menus);
        return result;
    }

    /**
     * 新增菜单页面
     * @param model
     * @param pId
     * @return
     */
    @GetMapping(value = "/add/{pId}")
    @RequiresPermissions("sys:menu:add")
    public String add(Model model, @PathVariable("pId") Integer pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectMenuByID(pId).getName());
        }
        return  "system/menu/add";
    }

    /**
     * 编辑菜单页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:menu:edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Menu menu = menuService.selectMenuByID(id);
        Integer pId = menu.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectMenuByID(pId).getName());
        }
        model.addAttribute("menu", menu);
        return "system/menu/edit";
    }

    /**
     * 新增菜单信息
     * @param menu
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:menu:add")
    public Result save(Menu menu) {
        if (menuService.insertMenu(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("sys:menu:edit")
    public Result update(Menu menu) {
        if (menuService.updateMenuByID(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "更新失败");
        }
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("sys:menu:remove")
    public Result remove(Integer id) {
        if (menuService.removeMenuByID(id) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "删除失败");
        }
    }

    /**
     * 权限树形菜单
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/tree")
    public Tree<Menu> tree() {
        Tree<Menu>  tree = menuService.selectMenuTree();
        return tree;
    }


    /**
     * 查询某角色权限树形菜单
     * @param roleId
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/tree/{roleId}")
    public Tree<Menu> tree(@PathVariable("roleId") Integer roleId) {
        Tree<Menu> tree = menuService.selectMenuTree(roleId);
        return tree;
    }

}
