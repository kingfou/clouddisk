package com.clouddisk.service;

import com.clouddisk.dao.FilesInfoDao;
import com.clouddisk.domain.FilesInfo;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/*
                          _ooOoo_
                         o8888888o
                         88" . "88
                         (| -_- |)
                         O\  =  /O
                      ____/`---'\____
                    .'  \\|     |//  `.
                   /  \\|||  :  |||//  \
                  /  _||||| -:- |||||-  \
                  |   | \\\  -  /// |   |
                  | \_|  ''\---/''  |   |
                  \  .-\__  `-`  ___/-. /
                ___`. .'  /--.--\  `. . __
             ."" '<  `.___\_<|>_/___.'  >'"".
            | | :  `- \`.;`\ _ /`;.`/ - ` : | |
            \  \ `-.   \_ __\ /__ _/   .-` /  /
       ======`-.____`-.___\_____/___.-`____.-'======
                          `=---='
       ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        author : kingfoulin    
       */

@Service
public class FilesInfoServiceImp implements FilesInfoService {

    @Autowired
    FilesInfoDao filesInfoDao;

    ApplicationContext applicationContext;
    @Override
    public boolean InserFilesInfo(FilesInfo filesInfo) {

        filesInfoDao.insert(filesInfo);
        return true;
    }

    @Override
    public List<FilesInfo> getAllFilesInfoByUserId(Integer userId) {
        return filesInfoDao.getAllFilesInfoByUserId(userId);
    }

    @Override
    public boolean deleteFileByFileId(Integer fileId) {
        FilesInfo filesInfo = filesInfoDao.getFilesInfoByFileId(fileId);
        return filesInfoDao.deleteById(filesInfo)>0;

    }

    @Override
    public List<FilesInfo> getAllFilesInfoByFolderId(Integer folderId) {
        return filesInfoDao.getAllFilesInfoByFolderId(folderId);
    }

    @Override
    public PageQuery<FilesInfo> getNoFoldFilesByUserId(PageQuery pageQuery,Integer userId) {
        return filesInfoDao.getNoFoldFilesByUserId(pageQuery,userId);
    }
}
