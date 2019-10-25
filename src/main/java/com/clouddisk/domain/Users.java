package com.clouddisk.domain;


import org.beetl.sql.core.TailBean;

public class Users extends TailBean {
    public String name;
    public String password;
    public String email;
    public String stu_Numb;
    public Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStu_Numb() {
        return stu_Numb;
    }

    public void setStu_Numb(String stu_Numb) {
        this.stu_Numb = stu_Numb;
    }
}
