package com.clouddisk.service;

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

import com.clouddisk.dao.FoldersDao;
import com.clouddisk.domain.Folders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoldersServiceImp implements FoldersService{

    @Autowired
    private FoldersDao foldersDao;
    @Override
    public Folders getFolderByFolderId(Integer folderId) {
        return foldersDao.getFolderByFolderId(folderId);
    }

    @Override
    public List<Folders> getAllFoldersByUserId(Integer userId) {
        return foldersDao.getAllFoldersByUserId(userId);
    }
}
