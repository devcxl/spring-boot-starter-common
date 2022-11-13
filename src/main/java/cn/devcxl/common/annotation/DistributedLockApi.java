package cn.devcxl.common.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author devcxl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLockApi {
    /**
     * 锁key前缀
     */
    String key() default "lock_api";

    /**
     * 锁value
     */
    String value() default "lock_value";

    /**
     * 锁超时时间
     */
    long timeout() default 10;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 被加锁方法执行完是否立即释放锁
     */
    boolean immediatelyUnLock() default true;

    /**
     * 等待获取锁时间(秒)
     */
    long waitLockSecondTime() default 0;
}
