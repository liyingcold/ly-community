package com.ly.login.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 缓存提供类
 */
@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    /**
     * 写入缓存有效时间时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(String key, Object value,Long expireTime){
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        operations.set(key,value);
        redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
        return true;
    }

}
