package com.ly.login.service;

import com.ly.login.common.ServerResponse;
import com.ly.login.pojo.User;
import com.ly.login.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService {
     ServerResponse<List<User>> findAll();

//     登录
     ServerResponse<User> login(@Param("name")String name,@Param("password")String password);

//     注册
    ServerResponse<String> register(User user);

//    重置密码
    ServerResponse<String> changePasw(User user);

//    修改个人信息
    ServerResponse<String> changeUserInfo(UserInfo userInfo);
}
