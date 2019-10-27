package com.clouddisk.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author junhao Lin
 * @date 2019/10/26
 *
 * 测试用例
 * String accessKey = "1N07V6A6K7ZA15UP745J";
 * String secretKey = "kvBOGP5OBq7dqfAgqYB0meyDNnS92Kvegdg3ifNh";
 * String endPoint = "http://125.216.242.149:7480";
 * String bucketName = "testbucket";
 */
public class CephObjectControl {

    private AmazonS3 s3Client;

    public CephObjectControl(String accessKey, String secretKey, String endPoint) {
        AWSCredentials cephCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(cephCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, ""))
                .build();
    }


    /**
     * 列出path目录下所有子目录和文件
     * @param userName 用户名字
     * @param path 详细路径，必须以'/开头',以'/'结尾
     * @param bucketName bucket名称
     * @return 返回JSON格式的对象
     * 示例：
     * {
     *  "folderArray":[
     *      {"modify":"Fri Oct 25 21:42:10 CST 2019","name":"folder"}
     *  ],
     *  "fileArray":[
     *      {"modify":"Fri Oct 25 21:42:01 CST 2019","size":486,"name":"rarreg.key"}
     *  ]
     *}
     */
    public JSONObject listAllFoldersAndFiles(String bucketName, String userName, String path){
        JSONObject result  = new JSONObject();
        String actualPath = userName + path;
        if (!path.matches("^/([^|><?*\\\":\\\\/]*/)*")){
            result.put("return", "Path invalid");
            return result;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            result.put("return", "Bucket doesn't exist");
            return result;
        }else if (!s3Client.doesObjectExist(bucketName, actualPath)){
            result.put("return", "Folder doesn't exist");
            return result;
        }

        JSONArray folderArray = new JSONArray();
        JSONArray fileArray = new JSONArray();

        ListObjectsV2Result queryResult = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = queryResult.getObjectSummaries();
        for (S3ObjectSummary object : objects){
            String objectPath = object.getKey();
            //判断路径前缀是否符合
            if (objectPath.matches("^" + actualPath + "([^|><?*\\\":\\\\/]+)/?$")){
                if (objectPath.endsWith("/")){
                    //是目录
                    JSONObject folder = new JSONObject();
                    String[] strs = objectPath.split("/");
                    folder.put("name", strs[strs.length - 1]);
                    folder.put("modify", object.getLastModified().toString());
                    folderArray.add(folder);
                }else {
                    //是文件
                    JSONObject file = new JSONObject();
                    String[] strs = objectPath.split("/");
                    file.put("name", strs[strs.length - 1]);
                    file.put("modify", object.getLastModified().toString());
                    file.put("size", object.getSize());
                    fileArray.add(file);
                }
            }
        }
        result.put("folderArray", folderArray);
        result.put("fileArray", fileArray);
        return result;
    }


    /**
     * 创建目录,按顺序创建每级目录
     * @param userName 用户名称
     * @param path 详细路径，必须以'/开头',以'/'结尾
     * @param bucketName bucket名称
     * @return true 目录创建成功 | false 目录创建失败
     */
    public boolean createFolder(String bucketName, String userName, String path){
        String actualPath = userName + path;
        if (!path.matches("^/([^|><?*\\\":\\\\/]*/)*")){
            //path不以'/'结尾
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            //桶不存在
            return false;
        }else if (s3Client.doesObjectExist(bucketName, actualPath)){
            //目录已经存在
            return true;
        }

        String subPath = "";
        for (String str : actualPath.split("/")){
            subPath += (str + "/");
            createEmptyFolder(bucketName, subPath);
        }
        return true;
    }

    /**
     * 删除目录，如果目录下有文件则一并删除
     * @param userName 用户名称
     * @param path 详细路径，必须以'/开头',以'/'结尾
     * @param bucketName bucket名称
     * @return true 目录删除成功 | false 目录删除失败
     */
    public boolean deleteFolder(String bucketName, String userName, String path){
        String actualPath = userName + path;
        if (!path.matches("^/([^|><?*\\\":\\\\/]*/)*")){
            //path不以'/'结尾
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            //桶不存在
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, actualPath)){
            //目录不存在
            return true;
        }

        ListObjectsV2Result queryResult = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = queryResult.getObjectSummaries();
        for (S3ObjectSummary object : objects){
            if (object.getKey().matches("^" + actualPath + ".*")){
                try{
                    s3Client.deleteObject(bucketName, object.getKey());
                } catch (AmazonServiceException e){
                    System.err.println(e.getErrorMessage());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 上传文件
     * @param userName 用户名
     * @param filePath 文件路径，必须以'/'开头，不以'/'结尾
     * @param bucketName bucket名称
     * @return true 上传成功 | false 上传失败
     */
    public boolean uploadFile(String bucketName, String userName, String filePath, InputStream inputStream) throws IOException {
        String actualFilePath = userName + filePath;
        if (!filePath.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, actualFilePath.substring(0, actualFilePath.lastIndexOf('/') + 1))){
            //目录不存在
            return false;
        }

        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, actualFilePath, inputStream, new ObjectMetadata());
            s3Client.putObject(request);
        }catch (AmazonServiceException ase){
            ase.printStackTrace();
            return false;
        }catch (AmazonClientException ace){
            ace.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 下载文件
     * @param userName 用户名称
     * @param filePath 文件路径，必须以'/'开始，不以'/'结尾
     * @param bucketName bucket名称
     * @return 返回InputStream 使用完后必须调用close()关闭输入流
     */
    public InputStream downloadFile(String bucketName, String userName, String filePath){
        String actualFilePath = userName + filePath;
        if (!filePath.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return null;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return null;
        }else if (!s3Client.doesObjectExist(bucketName, actualFilePath)){
            //文件不存在
            return null;
        }

        S3ObjectInputStream inputStream = null;
        try {
            S3Object object = s3Client.getObject(bucketName, actualFilePath);
            inputStream = object.getObjectContent();
        }catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
        }

        return inputStream;
    }


    /**
     * 删除文件
     * @param userName 用户名
     * @param filePath 文件路径，必须以'/'开始，不以'/'结尾
     * @param bucketName bucket名称
     * @return true 删除成功 | false 删除失败
     */
    public boolean deleteFile(String bucketName, String userName, String filePath){
        String actualFilePath = userName + filePath;
        if (!filePath.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, actualFilePath)){
            //文件不存在
            return true;
        }

        try {
            s3Client.deleteObject(bucketName, actualFilePath);
        } catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            return false;
        }
        return true;
    }

    /**
     * 重命名文件， oldName和newName的目录前缀一定要相同
     * @param userName 用户名称
     * @param oldName 文件当前的名称，必须以'/'开始，不以'/'结尾
     * @param newName 文件新的名称，必须以'/'开始，不以'/'结尾
     * @param bucketName bucket名称
     * @return true 成功 | false 失败
     */
    public boolean renameFile(String bucketName, String userName, String oldName, String newName){
        if (!oldName.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")
            || !newName.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, userName + oldName)){
            //文件不存在
            return false;
        }else if (!oldName.substring(0, oldName.lastIndexOf('/') + 1).equals(
                newName.substring(0, newName.lastIndexOf('/') + 1)
        )){
            //前缀不相同
            return false;
        }

        try {
            s3Client.copyObject(bucketName, userName + oldName, bucketName, userName + newName);
            s3Client.deleteObject(bucketName, userName + oldName);
        }catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            return false;
        }

        return true;
    }


    /**
     * 移动文件，移动后会删除源文件
     * @param userName 用户名
     * @param fromFile 源文件路径,包括文件名，必须以'/'开头，不以'/'结尾
     * @param toFile 目的文件路径，包括文件名，必须以'/'开头，不以'/'结尾
     * @param bucketName bucket名
     * @return true 成功 | false 失败
     */
    public boolean moveFile(String bucketName, String userName, String fromFile, String toFile){
        if (!fromFile.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")
                || !toFile.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, userName + fromFile)){
            //文件不存在
            return false;
        }

        //检查目的文件路径是否存在
        String destination = userName + '/';
        String[] splitString = toFile.split("/");
        //不包括最后的文件名
        for (int i = 1; i < splitString.length - 1; i++) {
            destination += splitString[i] + "/";
            createEmptyFolder(bucketName, destination);
        }

        try {
            s3Client.copyObject(bucketName, userName + fromFile, bucketName, userName + toFile);
            s3Client.deleteObject(bucketName, userName + fromFile);
        }catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            return false;
        }

        return true;
    }

    /**
     * 复制文件
     * @param userName 用户名
     * @param fromFile 源文件路径,包括文件名，必须以'/'开头，不以'/'结尾
     * @param toFile 目的文件路径，包括文件名，必须以'/'开头，不以'/'结尾
     * @param bucketName bucket名
     * @return true 成功 | false 失败
     */
    public boolean copyFile(String bucketName, String userName, String fromFile, String toFile){
        if (!fromFile.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")
                || !toFile.matches("^/([^|><?*\\\":\\\\/]*/)*[^|><?*\\\":\\\\/]+$")){
            return false;
        }else if (!s3Client.doesBucketExistV2(bucketName)){
            return false;
        }else if (!s3Client.doesObjectExist(bucketName, userName + fromFile)){
            //文件不存在
            return false;
        }

        //检查目的文件路径是否存在
        String destination = userName + '/';
        String[] splitString = toFile.split("/");
        //不包括最后的文件名,以及第一个空字符
        for (int i = 1; i < splitString.length - 1; i++) {
            destination += splitString[i] + "/";
            createEmptyFolder(bucketName, destination);
        }

        try {
            s3Client.copyObject(bucketName, userName + fromFile, bucketName, userName + toFile);
        }catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            return false;
        }

        return true;
    }

    /**
     * 创建空目录，实际上是创建一个以'/'结尾的文件
     * @param bucketName bucket名
     * @param path 路径，必须以'/'结尾
     */
    private void createEmptyFolder(String bucketName, String path){
        if (!path.endsWith("/")){
            return;
        }
        if (!s3Client.doesObjectExist(bucketName, path)){
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest request = new PutObjectRequest(bucketName,
                    path,
                    new ByteArrayInputStream("".getBytes()),
                    metadata);
            s3Client.putObject(request);
        }
    }
}
