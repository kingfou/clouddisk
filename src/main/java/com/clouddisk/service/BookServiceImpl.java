package com.clouddisk.service;

import com.clouddisk.dao.BookDao;
import com.clouddisk.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kingfou on 2018/11/19.
 */

public class BookServiceImpl implements BookService{
    @Autowired
    BookDao book;

    @Override
    public Book getNameByAuthor(String name) {
        return book.selectBookByAuthor(name);
    }
}
