package com.lqs.demospringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // String的序列化
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // key采用StringRedis的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //value采用jackson序列化方式
        template.setValueSerializer(serializer);


        // hash的key采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        //hash的value也采用jackson序列化方式
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();


        return template;
    }
}
