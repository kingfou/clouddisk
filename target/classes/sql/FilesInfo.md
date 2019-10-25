
getFilesInfoByFileId
===
select * from Files_Info where fileid = #fileId#

getAllFilesInfoByUserId
===
select * from Files_Info where user_Id= #userId#

getAllFilesInfoByFolderId
===
select * from Files_Info where folder_Id= #folderId#

getNoFoldFilesByUserId
===
select * from Files_Info where user_Id= #userId# and folder_Id = ""
