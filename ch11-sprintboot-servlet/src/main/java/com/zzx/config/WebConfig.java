package com.zzx.config;

import com.zzx.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Configuration
public class WebConfig {
    /**
     * 注册Servlet对象
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //public ServletRegistrationBean(T servlet, String... urlMappings)
        //分别是servlet对象，url地址
        ServletRegistrationBean bean= new ServletRegistrationBean();
        bean.setServlet(new MyServlet());
        bean.addUrlMappings("/myServlet","/login");
        return bean;
    }
}
