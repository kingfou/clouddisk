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

import com.clouddisk.domain.FilesInfo;

import java.util.List;


public interface FilesInfoService {
    public boolean InserFilesInfo(FilesInfo filesInfo);
    List<FilesInfo> getAllFilesInfoByUserId(Integer userId);
    List<FilesInfo> getNoFoldFilesByUserId(Integer userId);
    public boolean deleteFileByFileId(Integer fileId);
    List<FilesInfo> getAllFilesInfoByFolderId(Integer folderId);

}
