package com.ly.login.service;

import com.ly.login.common.ServerResponse;
import com.ly.login.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService {
     ServerResponse<List<User>> findAll();

//     登录
     ServerResponse<User> login(@Param("name")String name,@Param("password")String password);

//     注册
    ServerResponse<String> register(User user);
}
