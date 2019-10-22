package com.clouddisk.dao;

import com.clouddisk.domain.Book;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * Created by kingfou on 2018/11/19.
 */

@SqlResource("book")
public interface BookDao extends BaseMapper<Book> {
    Book selectBookByAuthor(@Param("author") String author);

}
