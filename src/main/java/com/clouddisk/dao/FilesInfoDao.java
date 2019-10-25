package com.clouddisk.dao;


import com.clouddisk.domain.FilesInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;


@SqlResource("FilesInfo")
public interface FilesInfoDao extends BaseMapper<FilesInfo> {

    public FilesInfo getFilesInfoByFileId(@Param("fileId") Integer fileId);
    public List<FilesInfo> getAllFilesInfoByUserId(@Param("userId") Integer userId);
    public List<FilesInfo> getNoFoldFilesByUserId(@Param("userId") Integer userId);
    public List<FilesInfo> getAllFilesInfoByFolderId(@Param("folderId") Integer folderId);
}
