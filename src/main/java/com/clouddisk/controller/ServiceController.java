package com.clouddisk.controller;


import com.clouddisk.dao.BookDao;
import com.clouddisk.domain.Book;
import com.clouddisk.domain.Users;
import com.clouddisk.domain.XmlTable;
import com.clouddisk.service.UserInfoService;
import com.clouddisk.service.XmltableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * Created by kingfou on 2018/11/18.
 */
/**
 * 使用注解的方式进行开发。
 * 同时这里需要使用Controller，而不是RestController
 * 使用main函数启动我们的项目。
 * **/

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/home")
public class ServiceController {
    @Autowired
    BookDao bookdao;
    BufferedWriter bw=null;
    @Resource
    private XmltableService xmltableService; //自动的将实例注入了。我们直接调用接口即可。


    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    public String index(){
        Users si = userInfoService.getUserById(1);
        return "index";
    }



    @RequestMapping("/calloc")
    public String calloc(Model model){
        System.out.print("calloc");
        Book book=bookdao.selectBookByAuthor("林金福");
        model.addAttribute("book",book);
        System.out.print(book.getName());
        System.out.print("hello world");
        return "calloc";
    }


    @RequestMapping("/search")
    public String search(Model model,XmlTable xmlTable1){ //模型驱动的时候，会自动的注入到这个对应的pojo。



        //获取查询的方式： 按照名称，按照功能，按照需求 进行处理

        String serach_name= xmlTable1.getSearchname();

        List<XmlTable> xmlTable2=null;  //记录查询发挥的数据：

        if("byname".equals(serach_name)){
            //按照名称进行查询：
            xmlTable2=xmltableService.getAllMessageByName(xmlTable1.getName());
            System.out.println(xmlTable2.size());

        }else{
            xmlTable2=xmltableService.getAllMessageByUsage(xmlTable1.getFusage());

        }

        model.addAttribute("xmltable2",xmlTable2);
        model.addAttribute("xmltable1",xmlTable1);
        System.out.print(xmlTable1.getSearchname());
        return "search_result";









    }

    @RequestMapping("/mapping")
    public String mapping(Model model,String name) throws IOException{
        System.out.print(name);
        try{bw=new BufferedWriter(new FileWriter("input.log",true));}catch (IOException e){
            System.out.print(e);
        }
        bw.write(name);
        bw.newLine();
        bw.flush();
        bw.close();
        XmlTable allmsg=xmltableService.getOneMessageByName(name);

        XmlTable xmlTable=null;
        if(allmsg!=null){
            xmlTable=allmsg;
            String function=xmlTable.getFunction();
            function=function.replace("<", "&lt");
            function=function.replace(">", "&gt;");
            xmlTable.setFunction(function);
        }



        model.addAttribute("xmltable",xmlTable);
        //页面上的问题基本全是由于数据库中字符的带有一些 <>,<.H>等造成的。


        return "calloc";
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
