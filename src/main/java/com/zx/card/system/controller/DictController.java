package com.zx.card.system.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.system.model.Dict;
import com.zx.card.system.service.DictService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/system/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @GetMapping
    @RequiresPermissions(value = "sys:menu:menu")
    public String dict() {
        return  "system/dict/dict";
    }

    /**
     * 字典列表
     * @param page
     * @param dict
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("sys:dict:dict")
    public Result list(Page<Dict> page, Dict dict) {
        return dictService.selectDictByPage(page,dict);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("sys:dict:add")
    public String add() {
        return "system/dict/add";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:dict:edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Dict dict = dictService.selectDictByID(id);
        model.addAttribute("dict", dict);
        return "system/dict/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:dict:add")
    public Result saveDict(Dict dict) {
        return dictService.saveDict(dict);
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("sys:dict:edit")
    public Result update(Dict dict) {
        return dictService.updateDict(dict);
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("sys:dict:remove")
    public Result remove(Long id) {
        return dictService.removeDict(id);
    }


}
