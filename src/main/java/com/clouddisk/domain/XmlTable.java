package com.clouddisk.domain;


import java.io.Serializable;

/**
 * Created by kingfou on 2018/12/11.
 */

public class XmlTable implements Serializable{
    private int id;
    private String function;
    private String name;
    private String params;
    private String paramsfor;
    private String fusage;
    private String relevantfuncs;
    private String funcreturn;
    private String searchname;  //用于判断是按照那种需求进行查询的。

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getParamsfor() {
        return paramsfor;
    }

    public void setParamsfor(String paramsfor) {
        this.paramsfor = paramsfor;
    }

    public String getFusage() {
        return fusage;
    }

    public void setFusage(String fusage) {
        this.fusage = fusage;
    }

    public String getRelevantfuncs() {
        return relevantfuncs;
    }

    public void setRelevantfuncs(String relevantfuncs) {
        this.relevantfuncs = relevantfuncs;
    }

    public String getFuncreturn() {
        return funcreturn;
    }

    public void setFuncreturn(String funcreturn) {
        this.funcreturn = funcreturn;
    }
}
