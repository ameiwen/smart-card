package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.Supermarket;
import com.zx.card.service.ISupermarketService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manage/market")
public class MarketController {

    @Autowired
    private ISupermarketService supermarketService;

    @GetMapping
    public String library() {
        return  "manage/market/market";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<Supermarket> page, Supermarket supermarket) {
        return supermarketService.selectSupermarketByPage(page,supermarket);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "manage/market/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(Supermarket supermarket) {
        return supermarketService.saveSupermarket(supermarket);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Supermarket supermarket = supermarketService.selectSupermarket(id);
        request.setAttribute("market",supermarket);
        return "manage/market/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(Supermarket supermarket){
        return supermarketService.updateSupermarket(supermarket);
    }

}
