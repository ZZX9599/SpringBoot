package com.zzx.mvc.config;

import com.zzx.mvc.servlet.MyServlet;
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
    public ServletRegistrationBean getServlet(){
        ServletRegistrationBean servlet=new ServletRegistrationBean();
        servlet.setServlet(new MyServlet());
        servlet.addUrlMappings("/user/add");
        return servlet;
    }
}
