package com.ly.login.config;

import com.ly.login.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置注册

@Configuration
public class Config_  implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册拦截器到SpringMVC机制，返回一个拦截器注册
//        拦截所有登录注册请求
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/login/*");

    }
}
