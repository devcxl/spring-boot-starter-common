package cn.devcxl.common.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author devcxl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 是否只针对请求
     *
     * @return
     */
    boolean api();

    /**
     * 锁key前缀
     */
    String keyPrefix() default "lock_key";

    /**
     * 锁value
     */
    String valuePrefix() default "lock_value";

    /**
     * 锁超时时间
     */
    long timeout() default 10;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
