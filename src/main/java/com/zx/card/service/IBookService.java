package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Book;
import com.zx.card.model.BookType;
import com.zx.card.utils.Result;

import java.util.List;

public interface IBookService {

    Result selectBookByPage(Page<Book> pageInfo, Book book);

    List<BookType> selectAllBookType();

    Result saveBook(Book book);

    Book selectBook(Long id);

    Result updateBook(Book book);

    Result removeBook(Long id);

}
