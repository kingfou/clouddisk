package com.clouddisk.service;

import com.clouddisk.domain.Users;


public interface UserInfoService {
     Users getUserById(Integer userId);
     Users getUserByUserNameAndPassword(String name,String password);
}
