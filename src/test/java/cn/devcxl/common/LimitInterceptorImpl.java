package cn.devcxl.common;

import cn.devcxl.common.interceptor.LimitInterceptor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author devcxl
 */
@Component
public class LimitInterceptorImpl implements LimitInterceptor {
    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public RedisTemplate<String, Integer> loadRedisTemplate() {
        return redisTemplate;
    }

}
