package com.clouddisk.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clouddisk.domain.FilesInfo;
import com.clouddisk.domain.Folders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class JSONToDomain {
    public static List<FilesInfo> JSONTToFilesInfo(JSONArray jsonArray) {
        List<FilesInfo> filesInfos = new ArrayList<FilesInfo>();
        for (int i = 0; i < jsonArray.size(); i++) {
            FilesInfo filesInfo = new FilesInfo();
            filesInfo.setName(jsonArray.getJSONObject(i).getString("name"));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date newdate = new Date(jsonArray.getJSONObject(i).getString("modify"));
                filesInfo.setUploaddate(sdf.parse(sdf.format(newdate)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            filesInfo.setSize(Double.parseDouble(jsonArray.getJSONObject(i).getString("size")));
            filesInfos.add(filesInfo);
        }
        return filesInfos;
    }


    public static List<JSONObject> ListFilesJSONObject(JSONArray jsonArray) {
        List<JSONObject> filesInfos = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.size(); i++) {

            filesInfos.add(jsonArray.getJSONObject(i));
        }
        return filesInfos;
    }


    public static List<Folders> JSONTToFolder(JSONArray jsonArray) {
        List<Folders> folders = new ArrayList<Folders>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Folders folder = new Folders();
            folder.setName(jsonArray.getJSONObject(i).getString("name"));

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String tips = " the data format can not work well...";
                Date newdate = new Date(jsonArray.getJSONObject(i).getString("modify"));
                folder.setUploaddate(sdf.parse(sdf.format(newdate)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            folders.add(folder);
        }
        return folders;
    }


    public static List<JSONObject> ListFoldersJSONObject(JSONArray jsonArray) {
        List<JSONObject> folders = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.size(); i++) {
            String tip="";
            folders.add(jsonArray.getJSONObject(i));
        }
        return folders;
    }


}
