package com.ly.login.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CookieUtil {

    private final static String COOKIE_NAME="ly_login_token";

    /**
     * 写入cookie
     * @param response 响应(出
     * @param token    携带的token
     */
    public void intoLoginToken(HttpServletResponse response,String token){
        Cookie cookie=new Cookie(COOKIE_NAME,token);
        cookie.setPath("/");//设置在根目录
        cookie.setHttpOnly(true);//不允许通过脚本读取
        cookie.setMaxAge(60*60*24*365);//单位：s -1永久有效 0删除
        log.info("into cookieName:,cookieValue:",cookie.getName(),cookie.getValue());
        response.addCookie(cookie);
    }

    /**
     * 读取Cookie
     * @param request  请求(进)
     * @return cookie值
     */
    public String getLoginToken(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                log.info("get cookieName:,cookieValue:",cookie.getName(),cookie.getValue());
                if (StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    log.info("get cookieName:,cookieValue:",cookie.getName(),cookie.getValue());
                    return  cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     */
    public void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    log.info("del ",cookie.getName(),cookie.getValue());
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }


}
