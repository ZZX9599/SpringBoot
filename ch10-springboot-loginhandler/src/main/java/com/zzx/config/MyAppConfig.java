package com.zzx.config;

import com.zzx.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器对象  注入到容器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        //InterceptorRegistration--->拦截器注册中心
        InterceptorRegistration registration=registry.addInterceptor(interceptor);
        //添加拦截的请求uri地址
        String[] path=new String[]{"/user/**","/buy/**"};
        registration.addPathPatterns(path);
        //不拦截的地址
        String[] excludePath=new String[]{"/user/login/**"};
        registration.excludePathPatterns(excludePath);
    }
}
