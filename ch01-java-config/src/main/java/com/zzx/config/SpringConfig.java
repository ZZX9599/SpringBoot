package com.zzx.config;

import org.springframework.context.annotation.*;

import java.util.Date;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @Configuration 相当于spring.xml
 */
@Configuration
@ImportResource(value = "classpath:spring.xml")
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan(basePackages = "com.zzx.vo")
public class SpringConfig {
    /**
     * 方法：返回值是对象，在方法上面加上@Bean，返回值就会注入到spring容器
     * @Bean代表把返回值注入到spring容器
     * 属性：name【不写默认就是方法名，写了就代表对象名字】
     */
    @Bean(name = "date")
    public Date createDate(){
        Date date=new Date();
        return date;
    }
}
