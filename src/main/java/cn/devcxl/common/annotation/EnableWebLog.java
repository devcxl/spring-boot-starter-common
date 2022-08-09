package cn.devcxl.common.annotation;

import cn.devcxl.common.aop.WebLogAop;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启@WebLog注解实现记录请求
 *
 * @author devcxl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(WebLogAop.class)
public @interface EnableWebLog {
}
