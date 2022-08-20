package com.zzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zzx.dao","com.jxl.dao"})
public class MySpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBoot.class,args);
    }
}
