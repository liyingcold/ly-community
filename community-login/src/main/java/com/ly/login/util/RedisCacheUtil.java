package com.ly.login.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 缓存提供类
 */
@Slf4j
@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 写入缓存值
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
        redisTemplate.opsForValue().set(key,value,expireTime, TimeUnit.SECONDS);
        return true;
    }

    public String getCacheKey(String key){
        String value= (String) redisTemplate.opsForValue().get(key);
        return value;
    }
    /**
     * 删除key
     * @param key
     * @return
     */
    public boolean delCacheKey(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
        return true;
    }

    /**
     * 获取token有效期
     * @param key -2键不存在
     * @return
     */
    public long getExpireTime(String key){
        log.info(key);
        long timeOut=redisTemplate.getExpire(key);
        return timeOut;
    }

    /**
     * 获取token有效分钟数
     * @param key
     * @return
     */
    public long getExpireTimeMin(String key){
        long timeOut=redisTemplate.getExpire(key,TimeUnit.MINUTES);
        return timeOut;
    }
}
