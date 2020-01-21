package com.ly.login.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class TokenCache {

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;




}
