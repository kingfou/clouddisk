package com.clouddisk.dao;


import com.clouddisk.domain.Users;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("users")
public interface UsersDao extends BaseMapper<Users> {

    public Users getUserById(@Param("userId")Integer userId);
}
