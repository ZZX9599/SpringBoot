package com.zzx.web.config;

import com.zzx.web.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean();
        bean.setServlet(new MyServlet());
        bean.addUrlMappings("/myServlet");
        return bean;
    }
}
