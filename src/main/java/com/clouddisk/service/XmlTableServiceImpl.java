package com.clouddisk.service;

import com.clouddisk.dao.XmlTableDao;
import com.clouddisk.domain.XmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kingfou on 2018/12/11.
 */


@Service
public class XmlTableServiceImpl implements XmltableService{
    @Autowired
    private XmlTableDao xmlTableDao;

    @Override
    public XmlTable getOneMessageByName(String name) {
        return xmlTableDao.getOneMessageByName(name);
    }

    @Override
    public List<XmlTable> getAllMessageByName(String name) {
        return xmlTableDao.getAllMessageByName(name);
    }

    @Override
    public XmlTable getAllName() {
        return xmlTableDao.getAllName();
    }

    @Override
    public List<XmlTable> getAllMessageByUsage(String usage) {
        return xmlTableDao.getAllMessageByUsage(usage);
    }
}
