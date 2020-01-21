package com.ly.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//登录启动类
@SpringBootApplication
public class LoginApp {
    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }




}
