package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.BookType;
import com.zx.card.model.BorrowRecord;
import com.zx.card.service.IBookRecordService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manage/record")
public class BookRecordController {

    @Autowired
    private IBookRecordService bookRecordService;

    @GetMapping
    public String record() {
        return  "manage/record/record";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<BorrowRecord> page, BorrowRecord borrowRecord) {
        return bookRecordService.selectBorrowRecordByPage(page,borrowRecord);
    }

    @GetMapping(value = "/borrow")
    public String add() {
        return "manage/record/borrow";
    }


    @ResponseBody
    @PostMapping(value = "/cardInfo/{id}")
    public Result selectCardInfoById(@PathVariable("id") Long id){
        return bookRecordService.selectCardInfoById(id);
    }

    @ResponseBody
    @PostMapping(value = "/bookInfo/{id}")
    public Result selectBookInfoById(@PathVariable("id") Long id){
        return bookRecordService.selectBookInfoById(id);
    }

    @ResponseBody
    @PostMapping(value = "/save/{type}")
    public Result save(BorrowRecord borrowRecord,@PathVariable("type") String type) {
        return bookRecordService.saveBorrowRecord(borrowRecord,type);
    }

}
