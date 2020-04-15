package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.Canteen;
import com.zx.card.service.ICanteenService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manage/canteen")
public class CanteenController {

    @Autowired
    private ICanteenService canteenService;

    @GetMapping
    public String canteen() {
        return  "manage/canteen/canteen";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<Canteen> page, Canteen canteen) {
        return canteenService.selectCanteenByPage(page,canteen);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "manage/canteen/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(Canteen canteen) {
        return canteenService.saveCanteen(canteen);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Canteen canteen = canteenService.selectCanteen(id);
        request.setAttribute("canteen",canteen);
        return "manage/canteen/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(Canteen canteen){
        return canteenService.updateCanteen(canteen);
    }

}
