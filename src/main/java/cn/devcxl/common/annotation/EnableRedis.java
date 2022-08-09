package cn.devcxl.common.annotation;

import cn.devcxl.common.config.RedisConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author devcxl
 * 开启Redis缓存服务, 自动配置Redis序列化  redisTemplate
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RedisConfig.class)
public @interface EnableRedis {

}
