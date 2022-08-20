package com.zzx.web.config;

import com.zzx.web.filter.EncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Configuration
public class FilterConfig {
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new EncodingFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean
    public FilterRegistrationBean getSystemFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        //使用框架的字符集过滤器
        CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
        //指定字符集
        encodingFilter.setEncoding("utf-8");
        //指定request和response都使用这个字符集
        encodingFilter.setForceEncoding(true);
        bean.setFilter(encodingFilter);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
