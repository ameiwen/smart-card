package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.CardInfo;
import com.zx.card.service.ICardInfoService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/card/info")
public class CardController {

    @Autowired
    private ICardInfoService cardInfoService;

    @GetMapping
    public String info() {
        return  "card/info/info";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<CardInfo> page, CardInfo cardInfo) {
        return cardInfoService.selectCardInfoByPage(page,cardInfo);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "card/info/add";
    }

    @GetMapping(value = "/edit")
    public String edit() {
        return "card/info/edit";
    }

    @ResponseBody
    @PostMapping(value = "/selectUserInfoById/{id}/{role}")
    public Result getUserInfo(@PathVariable("id") Long id,@PathVariable("role") String role){
        return cardInfoService.selectUserInfoById(id, role);
    }

    @ResponseBody
    @PostMapping(value = "/selectCardInfoById/{id}")
    public Result selectCardInfoById(@PathVariable("id") Long id){
        return cardInfoService.selectCardInfoById(id);
    }


    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(CardInfo cardInfo) {
        return cardInfoService.saveCardInfo(cardInfo);
    }

    @ResponseBody
    @PostMapping(value = "/lost")
    public Result lost(Long id) {
        return cardInfoService.lostCardInfo(id);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result updateCardInfo(CardInfo cardInfo){
        return cardInfoService.updateCardInfo(cardInfo);
    }

}
