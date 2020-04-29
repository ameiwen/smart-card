package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.model.Book;
import com.zx.card.model.BookType;
import com.zx.card.service.IBookService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/manage/book")
public class BookController extends BaseController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public String book() {
        return  "manage/book/book";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<Book> page, Book book) {
        return bookService.selectBookByPage(page,book);
    }

    @GetMapping(value = "/add")
    public String add(HttpServletRequest request) {
        List<BookType> bookTypes = bookService.selectAllBookType();
        request.setAttribute("bookTypes",bookTypes);
        return "manage/book/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Book book = bookService.selectBook(id);
        List<BookType> bookTypes = bookService.selectAllBookType();
        request.setAttribute("book",book);
        request.setAttribute("bookTypes",bookTypes);
        return "manage/book/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(Book book){
        return bookService.updateBook(book);
    }

    @ResponseBody
    @PostMapping(value = "/remove")
    public Result remove(Long id) {
        return bookService.removeBook(id);
    }

}
