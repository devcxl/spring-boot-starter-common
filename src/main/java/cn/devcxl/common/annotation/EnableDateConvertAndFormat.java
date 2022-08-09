package cn.devcxl.common.annotation;

import cn.devcxl.common.config.LocalDateTimeConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author devcxl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(LocalDateTimeConfig.class)
public @interface EnableDateConvertAndFormat {
}
