package com.example.redislearn.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisSerializer<String> redisKeySerializer(){
        return RedisSerializer.string();
    }

    @Bean
    public RedisSerializer redisValueSerializer(){
        return new FastJsonRedisSerializer(Object.class);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //一般是<String, Object>
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //对key序列化
        redisTemplate.setKeySerializer(redisKeySerializer());
        redisTemplate.setHashKeySerializer(redisKeySerializer());

        //对value序列化，可以使用jackson或者fastjson
        redisTemplate.setValueSerializer(redisValueSerializer());
        redisTemplate.setHashValueSerializer(redisValueSerializer());

        //初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
