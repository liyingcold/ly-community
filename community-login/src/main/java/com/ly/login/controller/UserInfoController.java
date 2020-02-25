package com.ly.login.controller;

import com.ly.login.common.ServerResponse;
import com.ly.login.pojo.UserInfo;
import com.ly.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息（用户信息的查看修改）
 */

@Slf4j
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @PostMapping("/change")
    public ServerResponse<String> changerUserInfo(UserInfo userInfo){
        return userService.changeUserInfo(userInfo);
    }
}
