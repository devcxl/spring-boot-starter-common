package cn.devcxl.common.aop;

import cn.devcxl.common.annotation.DistributedLock;
import cn.devcxl.common.constant.SysConstant;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author devcxl
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {
    /**
     * StringRedisTemplate
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(cn.devcxl.common.annotation.DistributedLock)")
    public void apiPointCut() {

    }

    /**
     * 切点
     * 加分布式锁
     */
    @Around(value = "apiPointCut() && @annotation(distributedLock)")
    public Object apiAround(ProceedingJoinPoint pjp, DistributedLock distributedLock) throws Exception {

        boolean api = distributedLock.api();
        String key = null;
        String value = null;

        if (api) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            //访问者ip
            String clientIP = ServletUtil.getClientIP(request);
            //请求方式
            String method = request.getMethod();
            //请求路径
            String uri = request.getRequestURI();
            //获取入参
            Object[] args = pjp.getArgs();
            // 类名方法名作为分布式锁的key
            key = distributedLock.keyPrefix() + "_" + method + "_" + uri + "_" + JSONObject.wrap(args);
            value = distributedLock.valuePrefix();
        } else {
            Signature signature = pjp.getSignature();
            String className = signature.getDeclaringTypeName();
            String methodName = signature.getName();
            // 类名方法名作为分布式锁的key
            key = distributedLock.keyPrefix() + "_" + className + "." + methodName;
            value = distributedLock.valuePrefix() + "_" + SysConstant.HOSTNAME;
        }

        // 获取锁
        Boolean status = stringRedisTemplate.opsForValue().setIfAbsent(key, value, distributedLock.timeout(), distributedLock.timeUnit());
        if (!ObjectUtils.isEmpty(status) && status.equals(Boolean.TRUE)) {
            try {
                Object proceed = pjp.proceed();
                // 处理完成后解锁
                stringRedisTemplate.delete(key);
                return proceed;
            } catch (Throwable throwable) {
                log.error("key failure!! key{} error:{}", key, throwable.getMessage());
            }
        }
        log.warn("getLock failure!! key{}", key);
        throw new Exception("请勿重复操作!!");
    }

}
