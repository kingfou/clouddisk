package com.clouddisk.service;

import com.clouddisk.dao.FIlesInfoDao;
import com.clouddisk.domain.FilesInfo;
import org.springframework.beans.factory.annotation.Autowired;

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
public class FilesInofImp implements FilesInfoService {

    @Autowired
    FIlesInfoDao fIlesInfoDao;

    @Override
    public boolean InserFilesInfo(FilesInfo filesInfo) {
        fIlesInfoDao.insert(filesInfo);
        return true;
    }
}
