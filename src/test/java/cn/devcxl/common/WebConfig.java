package cn.devcxl.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author devcxl
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LimitInterceptorImpl limitInterceptor;

    @Resource
    private AuthInterceptorImpl authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
