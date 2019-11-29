package com.clouddisk.controller;


import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clouddisk.domain.*;
import com.clouddisk.service.FilesInfoService;
import com.clouddisk.service.FoldersService;
import com.clouddisk.service.UserInfoService;
import com.clouddisk.utils.JSONToDomain;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddisk.utils.CephObjectControl;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;


/**
 * Created by kingfou on 2018/11/18.
 */

/**
 * 使用注解的方式进行开发。
 * 同时这里需要使用Controller，而不是RestController
 * 使用main函数启动我们的项目。
 **/

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/home")
public class ServiceController {

    /**
     * 配置文件服务器
     */

    String accessKey = "1N07V6A6K7ZA15UP745J";
    String secretKey = "kvBOGP5OBq7dqfAgqYB0meyDNnS92Kvegdg3ifNh";
    String endPoint = "http://125.216.242.149:7480";
    CephObjectControl cephObjectControl = new CephObjectControl(accessKey, secretKey, endPoint);

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private FilesInfoService filesInfoService;

    @Resource
    private FoldersService foldersService;


    @RequestMapping(value = "/loginPage")
    public String logInPage() {
        return "signin";
    }


    @RequestMapping(value = "/loginCheck")
    public String logIn(Model model, String stuNumb, String password, HttpSession session) {
        Users user = userInfoService.getUserByUserNameAndPassword(stuNumb, password);
        if (user != null) {
            session.setAttribute("user", user);
            return showFolders(model, user, "/");
        } else {
            model.addAttribute("error_msg", "please check your student number and the password");
            return "signin";
        }
    }


    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);

        return "signin";
    }

    @RequestMapping("/showfolders")

    public String showFolders(Model model, Users user, String path) {
        user.setUserId(1);


        if (path == null || "".equals(path)) {path = "/";}else{
            path = path.replace("%20"," ");
        }

        System.out.println(path);

        //上一次请求中 文件的前缀记录  用于文件的全限定名称。
        String prefix = path;

        JSONObject jsonObject = cephObjectControl.listAllFoldersAndFiles("testbucket", "kingfou", path);
        String tips = "tips";
        String result = jsonObject.getString("return");

        JSONArray folderArray = (JSONArray) jsonObject.get("folderArray");
        JSONArray fileArray = (JSONArray) jsonObject.get("fileArray");

        List<Folders> allfolders = new ArrayList<Folders>();
        List<FilesInfo> noInFoldFiles = new ArrayList<FilesInfo>();
        if (folderArray != null) allfolders = JSONToDomain.JSONTToFolder(folderArray);
        if (fileArray != null) noInFoldFiles = JSONToDomain.JSONTToFilesInfo(fileArray);


        model.addAttribute("allfolders", allfolders);
        model.addAttribute("noInFoldFiles", noInFoldFiles);
        model.addAttribute("user", user);
        model.addAttribute("prefix", prefix.replace(" ","%20"));
        return "index";
    }


    @RequestMapping("/AjaxShowFolders")
    @ResponseBody
    /***
     * path : 路径前缀 以"/"开始 以"/"结束
     */

    public JSONObject ajaxShowFolders(String path) {
        //for test
        if ("".equals(path) || path == null) {
            String tips = "";
            path = "/";
        }

        //上一次请求中 文件的前缀记录  用于文件的全限定名称。
        String prefix = path;

        JSONObject jsonObject = cephObjectControl.listAllFoldersAndFiles("testbucket", "kingfou", path);

        JSONArray folderArray = (JSONArray) jsonObject.get("folderArray");
        JSONArray fileArray = (JSONArray) jsonObject.get("fileArray");

        List<JSONObject> allfolders = new ArrayList<JSONObject>();
        List<JSONObject> noInFoldFiles = new ArrayList<JSONObject>();

        if (folderArray != null) allfolders = JSONToDomain.ListFoldersJSONObject(folderArray);
        if (fileArray != null) noInFoldFiles = JSONToDomain.ListFilesJSONObject(fileArray);

        JSONObject result = new JSONObject();
        result.put("allfolders", allfolders);
        result.put("noInFoldFiles", noInFoldFiles);

        return result;


    }


    @RequestMapping(value = "/uploadfile")
    public String uploadFile(Model model, @RequestParam("uploadfile") MultipartFile file, Users user, String prefix) throws Exception {
        if (file == null) {
            System.out.print("file is null...");
        } else {

            // get file information
            String file_name = file.getOriginalFilename();
            // save the file to Ceph server

            prefix = prefix.replace("%20"," ");//手动转义
            boolean result = cephObjectControl.uploadFile("testbucket", "kingfou", prefix + file_name, file.getInputStream());
            System.out.print(result);
        }
        System.out.println("upload..."+prefix);
        return showFolders(model, user, prefix);

    }

    @RequestMapping(value = "/AjaxUploadFile")
    @ResponseBody
    /**
     * MultipartFile file ：表单提交的 type=file 的熟悉
     * prefix ： 路径前缀 以"/"开始， 以"/"结束。
     * */
    public boolean uploadFile(@RequestParam("uploadfile") MultipartFile file, String prefix) throws Exception {
        if (file == null) {
            return false;
        } else {

            // get file information
            String file_name = file.getOriginalFilename();
            // save the file to Ceph server
            boolean result = cephObjectControl.uploadFile("testbucket", "kingfou", prefix + file_name, file.getInputStream());
            System.out.print(result);
            return true;
        }


    }

    @RequestMapping(value = "/downloadFile", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    /**
     * path : 文件的全路径。 /test/xxx.xxx
     * filename :文件名称 xxx.xxx 的形式。可以省略。
     */

    public String downloadFile(HttpServletResponse response, String path, String filename) throws Exception {

        //手动转义
        path = path.replace("%20"," ");

        if ("".equals(filename) || filename == null) {
            String[] subpath = path.split("/");
            filename = subpath[subpath.length - 1];
            System.out.print(filename);
        }

//        filename = URLEncoder.encode(filename.trim(), "UTF-8");
        System.out.print(filename);
        InputStream fis = cephObjectControl.downloadFile("testbucket", "kingfou", path);

        if (fis == null) {
            return "error";
        }
        // 将数据流交付OutputStream
        response.reset();
        response.setContentType("application/force-download");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename,"utf-8"));
        int size = 1024;
        byte[] buffer = new byte[size];
        OutputStream fiss = response.getOutputStream();
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fiss.write(buffer, 0, len);
        }
        fis.close();
        fiss.close();


        return "successful";
    }

    @RequestMapping(value = "listAllFoldAndfiles")
    public String listAllFoldAndfiles(String path) {
        JSONObject jsonObject = cephObjectControl.listAllFoldersAndFiles("testbucket", "kingfou", path);
        JSONArray folderArray = (JSONArray) jsonObject.get("folderArray");
        JSONArray fileArray = (JSONArray) jsonObject.get("fileArray");

        //解析 json 数据
        System.out.print(folderArray.toString());
        System.out.print(fileArray.toString());
        List<Folders> filesInfos = JSONToDomain.JSONTToFolder(folderArray);
        for (Folders f : filesInfos) {
            System.out.println(f.getName());
            System.out.println(f.getUploaddate());

        }

        return "";
    }

    @RequestMapping(value = "createFolder")
    public String createFolder(Model model, Users usre, String prefix, String foldName) {
        //获取当前文件夹的全限定路径
        System.out.print(prefix);
        cephObjectControl.createFolder("testbucket", "kingfou", prefix + foldName + "/");
        return showFolders(model, usre, prefix);
    }

    @RequestMapping(value = "AjaxCreateFolder")
    @ResponseBody
    /**
     * prefix : 路径前缀，以 "/" 开始 以"/"结束。
     * foldName :文件名称
     */
    public boolean AjaxCreateFolder(String prefix, String foldName) {
        //获取当前文件夹的全限定路径
        System.out.print(prefix);
        return cephObjectControl.createFolder("testbucket", "kingfou", prefix + foldName + "/");

    }


    @RequestMapping(value = "/deleteFolder")
    public String deleteFolder(Model model, Users user, String prefix, String foldName) {
        cephObjectControl.deleteFolder("testbucket", "kingfou", prefix + foldName + "/");

        return showFolders(model, user, prefix);
    }

    @RequestMapping(value = "/AjaxDeleteFolder")
    @ResponseBody
    /**
     * preifx 路径前缀 文件夹当前所在路径。
     * foleName: 文件夹名称
     * */
    public boolean AjaxDeleteFolder(String prefix, String foldName) {
        return cephObjectControl.deleteFolder("testbucket", "kingfou", prefix + foldName + "/");
    }


    @RequestMapping(value = "/deleteFile")
    public String deleteFile(Model model, Users user, String prefix, String fileName) {
        System.out.print(prefix + fileName);
        prefix = prefix.replace("%20"," ");
        cephObjectControl.deleteFile("testbucket", "kingfou", prefix + fileName);
        return showFolders(model, user, prefix);
    }


    @RequestMapping(value = "/AjaxDeleteFile")
    @ResponseBody
    /**
     * path ：文件的全路径名称。 /test/subtest/xxx.xxx
     * */
    public boolean AjaxDeleteFile(String path) {
        return cephObjectControl.deleteFile("testbucket", "kingfou", path);

    }


}


/*
 * read me
 * 静态文件一律放在static
 * HTML文件放在template
 * sql文件放在sql
 * service是接口层
 * serviceImpl是实现层
 * DAO是接口层，直接注入可以获得实例这是beet l提供的技术
 * domain层是实体层。映射发生在DAO层。
 * control层中直接调用service层接口，采用自动注入的方式即可获得实例。
 * 注意的是beet l中需要的是html 而且它的js和css等文件（静态）文件需要直接放在static 文件中，这样spring boot 会自动的读取，
 * spring boot 会自动的和配置好的tomcat自动启动。
 * * * */
