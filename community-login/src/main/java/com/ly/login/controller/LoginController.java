package com.ly.login.controller;


import com.ly.login.common.ServerResponse;

import com.ly.login.pojo.User;
import com.ly.login.service.UserService;
import com.ly.login.service.UserServiceImpl;
import com.ly.login.util.CookieUtil;
import com.ly.login.util.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController//返回json/xml数据发送到前台页面
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private CookieUtil cookieUtil;


    /**
     * 查看
     */
    @GetMapping("/findAll")
    public ServerResponse<List<User>> getAllUserInfo(){
        return userService.findAll();
    }

    /**
     * 登录
     * 第一次登录时，生成uuid,一个给cookie(token),一个给redis(token)
     */
    @PostMapping("/login")
    public ServerResponse<User> loginUser(String name,String password, HttpSession httpSession, HttpServletRequest request, HttpServletResponse resp){
        ServerResponse<User> response = userService.login(name,password);
        if (response.isSuccess()){
            String token=UUID.randomUUID().toString();
            cookieUtil.intoLoginToken(resp,token);
            redisCacheUtil.set(token,name,(long) 1800);
            log.info("value:login_token_"+name+"----key:"+redisCacheUtil.getCacheKey(token));
        }
        return response;
    }

    /**
     * 退出登录
     * 退出时删除cookie和redis中的token
     */
    @PostMapping("/logout")
    public ServerResponse<String> logout(String name,HttpServletRequest request,HttpServletResponse response){
        String token=cookieUtil.getLoginToken(request);
        cookieUtil.delLoginToken(request,response);
        redisCacheUtil.delCacheKey(token);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ServerResponse<String> register( User user){
        return userService.register(user);
    }

    /**
     * 修改密码
     *
     */
    @PostMapping("/changePasw")
    public ServerResponse<String> changePassword(User user){
        return  userService.changePasw(user);
    }

    /**
     * 修改个人信息
     */
    public ServerResponse<String> changeUserInfo(User user){
        return null;
    }








}
