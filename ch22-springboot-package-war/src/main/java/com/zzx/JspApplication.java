package com.zzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ZZX
 */
@SpringBootApplication
public class JspApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JspApplication.class, args);
    }

    /**
     * 要想在自己的Tomcat运行，先继承SpringBootServletInitializer，重写configure方法
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JspApplication.class);
    }
}
