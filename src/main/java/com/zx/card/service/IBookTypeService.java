package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.BookType;
import com.zx.card.utils.Result;

public interface IBookTypeService {

    Result selectBookTypeByPage(Page<BookType> pageInfo, BookType bookType);

    Result saveBookType(BookType bookType);

    BookType selectBookType(Long id);

    Result updateBookType(BookType bookType);
}
