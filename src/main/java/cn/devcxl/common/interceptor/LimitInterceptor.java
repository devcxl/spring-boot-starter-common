package cn.devcxl.common.interceptor;

import cn.devcxl.common.annotation.Limit;
import cn.devcxl.common.exception.TooManyRequestsException;
import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 接口限流
 *
 * @author devcxl
 */
public interface LimitInterceptor extends HandlerInterceptor {

    String LIMIT_KEY_TEMPLATE = "limit_%s_%s";

    /**
     * 加载Redis
     *
     * @return RedisTemplate
     */
    RedisTemplate<String, Integer> loadRedisTemplate();

    /**
     * 检查API限制
     *
     * @param limit    注解
     * @param limitKey RedisKey
     * @return 是否拦截
     */
    default boolean checkLimit(Limit limit, String limitKey) {
        RedisTemplate<String, Integer> redisTemplate = loadRedisTemplate();
        int max = limit.max();
        int time = limit.time();
        TimeUnit timeUnit = limit.timeUnit();
        Integer count = redisTemplate.opsForValue().get(limitKey);
        if (count != null) {
            if (count < max) {
                Long expire = redisTemplate.getExpire(limitKey);
                if (expire != null && expire <= 0) {
                    redisTemplate.opsForValue().set(limitKey, 1, time, timeUnit);
                } else {
                    redisTemplate.opsForValue().set(limitKey, ++count, time, timeUnit);
                }
            } else {
                throw new TooManyRequestsException();
            }
        } else {
            redisTemplate.opsForValue().set(limitKey, 1, time, timeUnit);
        }
        return true;
    }

    /**
     * default preHandle
     *
     * @param request  请求
     * @param response 返回
     * @param handler  请求方法
     * @return 是否拦截
     */
    @Override
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Limit limit = method.getAnnotation(Limit.class);
            if (limit == null) {
                return true;
            } else {
                String limitKey = String.format(
                        LIMIT_KEY_TEMPLATE,
                        request.getRequestURI(),
                        ServletUtil.getClientIP(request)
                );
                return checkLimit(limit, limitKey);
            }
        } else {
            return false;
        }
    }

}
