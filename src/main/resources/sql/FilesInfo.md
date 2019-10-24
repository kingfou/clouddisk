
getFilesInfoByFileId
===
select * from Files where fileid = #fileId#

getAllFilesInfoByUserId
===
select * from Files where user_Id= #userId#

getAllFilesInfoByFolderId
===
select * from Files where folder_Id= #folderId#

getNoFoldFilesByUserId
===
select * from Files where user_Id= #userId# and folder_Id = NULL
