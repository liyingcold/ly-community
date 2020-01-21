package com.ly.login.controller;


import com.ly.login.common.ServerResponse;
import com.ly.login.pojo.User;
import com.ly.login.service.UserService;
import com.ly.login.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


@RestController//返回json/xml数据发送到前台页面
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 查看
     */
    @GetMapping("/findAll")
    public ServerResponse<List<User>> getAllUserInfo(){

        return userService.findAll();
    }



    /**
     * 登录
     */
    @PostMapping("/login")
    public ServerResponse<User> loginUser(String username,String password, HttpSession httpSession, HttpServletRequest request, HttpServletResponse resp){
        ServerResponse<User> response = userService.login(username,password);
        return response;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ServerResponse<String> register( User user){
        String m=user.getName();
        return userService.register(user);

    }






}
