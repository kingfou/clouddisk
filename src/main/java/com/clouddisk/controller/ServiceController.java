package com.clouddisk.controller;


import com.clouddisk.domain.*;
import com.clouddisk.service.FilesInfoService;
import com.clouddisk.service.FoldersService;
import com.clouddisk.service.UserInfoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.util.Date;
import java.util.List;


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


    @Resource
    private UserInfoService userInfoService;

    @Resource
    private FilesInfoService filesInfoService;

    @Resource
    private FoldersService foldersService;

    @RequestMapping("/showfolders")
    public String showFolders(Model model, Users user) {
        user.setUserId(1);

        List<Folders> allfolders = foldersService.getAllFoldersByUserId(1);
        List<FilesInfo> noInFoldFiles;
        noInFoldFiles = filesInfoService.getNoFoldFilesByUserId(user.getUserId());

        model.addAttribute("allfolders", allfolders);
        model.addAttribute("noInFoldFiles", noInFoldFiles);
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/showfiles")
    public String showFiles(Model model, Integer folderId) {

        System.out.print(folderId);
        List<Folders> allfolders = foldersService.getAllFoldersByUserId(1);
        List<FilesInfo> allfiles = filesInfoService.getAllFilesInfoByFolderId(folderId);
        model.addAttribute("allfolders", allfolders);
        model.addAttribute("allfiles", allfiles);
        return "fold";
    }


    @RequestMapping(value = "/uploadfile")
    public String uploadFile(Model model, @RequestParam("uploadfile") MultipartFile file, Users user) throws Exception {
        if (file == null) {
            System.out.print("file is null...");
        } else {

            // get file information
            long size = file.getSize() / 1000;
            String file_name = file.getOriginalFilename();
            String path = "H://" + "upload/" + file_name;
            FilesInfo filesInfo = new FilesInfo();

            filesInfo.setUserId(user.getUserId());
            filesInfo.setFolderId(1);
            filesInfo.setName(file_name);
            filesInfo.setPath(path);
            filesInfo.setUploaddate(new Date());
            filesInfo.setSize((double) size);


            file.transferTo(new File(path));

            // save in database
            filesInfoService.InserFilesInfo(filesInfo);


        }

        List<Folders> allfolders = foldersService.getAllFoldersByUserId(1);
        List<FilesInfo> noInFoldFiles = filesInfoService.getNoFoldFilesByUserId(user.getUserId());

        model.addAttribute("allfolders", allfolders);
        model.addAttribute("noInFoldFiles", noInFoldFiles);
        model.addAttribute("user", user);
        return "index";
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
