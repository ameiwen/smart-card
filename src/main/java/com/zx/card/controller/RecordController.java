package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.AssetHistory;
import com.zx.card.model.CardInfo;
import com.zx.card.service.IRecordService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/card/record")
public class RecordController {

    @Autowired
    private IRecordService recordService;

    @GetMapping
    public String record() {
        return  "card/record/record";
    }


    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<AssetHistory> page, AssetHistory assetHistory) {
        return recordService.selectRecordByPage(page,assetHistory);
    }


}
