package com.clouddisk.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddisk.utils.CephObjectControl;

import java.io.*;
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
            return showFolders(model, user,"/");
        } else {
            model.addAttribute("error_msg", "please check your student number and the password");
            return "signin";
        }
    }

    @RequestMapping("/showfolders")

    public String showFolders(Model model, Users user, String path) {
        user.setUserId(1);

        //for test
        if(path==null||"".equals(path)) path="/";


        JSONObject jsonObject = cephObjectControl.listAllFoldersAndFiles("testbucket", "kingfou", path);
        String tips= "tips";
        String result = jsonObject.getString("return");
        System.out.print(result);
        JSONArray folderArray = (JSONArray) jsonObject.get("folderArray");
        JSONArray fileArray = (JSONArray) jsonObject.get("fileArray");

        List<Folders> allfolders = new ArrayList<Folders>();
        List<FilesInfo> noInFoldFiles = new ArrayList<FilesInfo>();
        if (folderArray!=null) allfolders = JSONToDomain.JSONTToFolder(folderArray);
        if (fileArray!=null) noInFoldFiles = JSONToDomain.JSONTToFilesInfo(fileArray);


        model.addAttribute("allfolders", allfolders);
        model.addAttribute("noInFoldFiles", noInFoldFiles);
        model.addAttribute("user", user);
        return "index";
    }


    @RequestMapping("/ajaxshowfolders")
    @ResponseBody
    public Map<String, Object> ajaxShowFolders(Users user, PageQuery pageQuery) {
        System.out.print(user.getUserId() + "" + pageQuery.getPageNumber());
        pageQuery.setPageSize(5);
        List<Folders> allfolders = foldersService.getAllFoldersByUserId(user.getUserId());
        PageQuery<FilesInfo> noInFoldFiles;
        noInFoldFiles = filesInfoService.getNoFoldFilesByUserId(pageQuery, user.getUserId());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("pageQuery", pageQuery);
        map.put("allfolders", allfolders);
        map.put("noInFoldFiles", noInFoldFiles.getList());
        return map;
    }

    @RequestMapping("/showfiles")
    public String showFiles(Model model, Integer folderId, Users user, PageQuery pageQuery) {

        pageQuery.setPageSize(5);
        System.out.print(folderId);
        List<Folders> allfolders = foldersService.getAllFoldersByUserId(1);
        List<FilesInfo> allfiles = filesInfoService.getAllFilesInfoByFolderId(folderId);
        model.addAttribute("allfolders", allfolders);
        model.addAttribute("allfiles", allfiles);
        model.addAttribute("pageQuery", pageQuery);
        model.addAttribute("user", user);
        return "fold";
    }


    @RequestMapping(value = "/uploadfile")
    public String uploadFile(Model model, @RequestParam("uploadfile") MultipartFile file, Users user, PageQuery pageQuery) throws Exception {
        if (file == null) {
            System.out.print("file is null...");
        } else {

            // get file information
            long size = file.getSize() / 1000;
            String file_name = file.getOriginalFilename();
            String path = "/kingfou/" + file_name;
            FilesInfo filesInfo = new FilesInfo();

            filesInfo.setUserId(user.getUserId());
            filesInfo.setFolderId(1);
            filesInfo.setName(file_name);
            filesInfo.setPath(path);
            filesInfo.setUploaddate(new Date());
            filesInfo.setSize((double) size);

            // save the file to Ceph server
            boolean result = cephObjectControl.uploadFile("testbucket", "kingfou", "/" + file_name, file.getInputStream());
            System.out.print(result);

            // save in database
            filesInfoService.InserFilesInfo(filesInfo);


        }

        List<Folders> allfolders = foldersService.getAllFoldersByUserId(1);
        PageQuery<FilesInfo> noInFoldFiles = filesInfoService.getNoFoldFilesByUserId(pageQuery, user.getUserId());

        model.addAttribute("allfolders", allfolders);
        model.addAttribute("noInFoldFiles", noInFoldFiles.getList());
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public String downloadFile(HttpServletResponse response) throws Exception {

        InputStream fis = cephObjectControl.downloadFile("testbucket", "kingfou", "/D_1shot.py");

        // 将数据流交付OutputStream
        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + "D_1shot.py");
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
    public String createFolder() {
        cephObjectControl.createFolder("testbucket", "kingfou", "/test5/");
        return "";
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
