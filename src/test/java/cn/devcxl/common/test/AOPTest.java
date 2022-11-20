package cn.devcxl.common.test;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AOPTest {

    @Pointcut("@annotation(cn.devcxl.common.test.A)")
    public void pointCut() {

    }

    @Around(value = "pointCut() && @annotation(a)")
    public Object processed(ProceedingJoinPoint joinPoint, A a){



        Signature signature = joinPoint.getSignature();
        log.info("signature:{}.{}",signature.getDeclaringTypeName(),signature.getName());

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return proceed;
    }
}

