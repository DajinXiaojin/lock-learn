package com.example.redislearn.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisUtils {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 添加到缓存
     * @param key
     * @param value
     */
    public void set(final String key, final Object value){
        redisTemplate.opsForValue().set(key, value);
    }

}
