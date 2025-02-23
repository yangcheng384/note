package com.misc.note.common.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 设置键值对（带过期时间）
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 设置键值对（不过期）
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 获取键对应的值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除键
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 判断键是否存在
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 设置键的过期时间
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    // 获取键的剩余过期时间
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
}


