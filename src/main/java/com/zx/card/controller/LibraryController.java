package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.model.Library;
import com.zx.card.service.ILibraryService;
import com.zx.card.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manage/library")
public class LibraryController {

    @Autowired
    private ILibraryService libraryService;

    @GetMapping
    public String library() {
        return  "manage/library/library";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result list(Page<Library> page, Library library) {
        return libraryService.selectLibraryByPage(page,library);
    }

    @GetMapping(value = "/add")
    public String add() {
        return "manage/library/add";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(Library library) {
        return libraryService.saveLibrary(library);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Library library = libraryService.selectLibrary(id);
        request.setAttribute("library",library);
        return "manage/library/edit";
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(Library library){
        return libraryService.updateLibrary(library);
    }

}
