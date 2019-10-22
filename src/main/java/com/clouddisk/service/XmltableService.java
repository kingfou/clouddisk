package com.clouddisk.service;


import com.clouddisk.domain.XmlTable;

import java.util.List;

/**
 * Created by kingfou on 2018/12/11.
 */
public interface XmltableService {
    List<XmlTable>getAllMessageByName(String name);
    XmlTable getAllName();
    List<XmlTable> getAllMessageByUsage(String usage);
    XmlTable getOneMessageByName(String name);
}
