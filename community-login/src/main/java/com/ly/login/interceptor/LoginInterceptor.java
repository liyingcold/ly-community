package com.ly.login.interceptor;

import com.ly.login.common.ServerResponse;
import com.ly.login.dao.UserMapper;
import com.ly.login.pojo.User;
import com.ly.login.util.CookieUtil;
import com.ly.login.util.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        log.info("preHandle```````");
        log.info(String.valueOf(request.getSession().getId()));
/**
 * 如果登录过了，redis里面有sessionId,可不用登陆
 * 如果没有登录过就进行登录，拦截需要登录的界面，而不是登陆界面
*/
        String token=cookieUtil.getLoginToken(request);
        log.info(token);
        if (token != null){
            long time=redisCacheUtil.getExpireTime(token);
//        有效期之内,不用进行登录
            if (time>0){
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//        log.info("postHandle```````");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        log.info("afterCompletion```````");
    }
}
