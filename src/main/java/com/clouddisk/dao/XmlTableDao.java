package com.clouddisk.dao;

import com.clouddisk.domain.XmlTable;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * Created by kingfou on 2018/12/11.
 */

@SqlResource("xmltable")
public interface XmlTableDao extends BaseMapper<XmlTable> {
    // 按照名字查询
    List<XmlTable> getAllMessageByName(@Param("name") String name);

    //按照功能查询

    List<XmlTable> getAllMessageByUsage(@Param("usage") String usage);

    //按照描述查询
    List<XmlTable> getAllMessageByDescription(@Param("description") String description);

    //得到确定的函数
    XmlTable getOneMessageByName(@Param("name") String name);
    XmlTable getAllName();


}
