package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.model.BookType;
import com.zx.card.service.IBookTypeService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manage/type")
public class BookTypeController extends BaseController {

    @Autowired
    private IBookTypeService bookTypeService;

    @GetMapping
    public String type() {
        return  "manage/type/type";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<BookType> page, BookType bookType) {
        return bookTypeService.selectBookTypeByPage(page,bookType);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "manage/type/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(BookType bookType) {
        return bookTypeService.saveBookType(bookType);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        BookType bookType = bookTypeService.selectBookType(id);
        request.setAttribute("bookType",bookType);
        return "manage/type/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(BookType bookType){
        return bookTypeService.updateBookType(bookType);
    }

}
