package com.clouddisk.service;

import com.clouddisk.dao.UsersDao;
import com.clouddisk.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    private UsersDao userInfoDao;

    @Override
    public Users getUserById(Integer userId) {
        return userInfoDao.getUserById(userId);
    }
}
