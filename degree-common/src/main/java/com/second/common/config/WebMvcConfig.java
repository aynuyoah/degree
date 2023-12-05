package com.second.common.config;

import com.second.common.aop.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] swaggerExcludePathPatterns = {
                "/doc.html", "/swagger**/**", "/swagger-resources/**", "/webjars/**", "/v3/**", "/favicon.ico"
        };
        // 登录操作不拦截
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/sys/login")
                .excludePathPatterns("/sys/register")
                .excludePathPatterns(swaggerExcludePathPatterns)
                .excludePathPatterns("/csrf/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}

