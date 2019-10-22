package com.clouddisk.domain;


import java.io.Serializable;

public class Users implements Serializable {
    public String name;
    public String password;
    public String email;
    public String stu_numb;

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

    public String getStu_numb() {
        return stu_numb;
    }

    public void setStu_numb(String stu_numb) {
        this.stu_numb = stu_numb;
    }
}
