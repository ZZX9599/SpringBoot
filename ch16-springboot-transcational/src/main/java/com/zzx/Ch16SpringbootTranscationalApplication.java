package com.zzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZZX
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.zzx.dao"})
public class Ch16SpringbootTranscationalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch16SpringbootTranscationalApplication.class, args);
    }

}
