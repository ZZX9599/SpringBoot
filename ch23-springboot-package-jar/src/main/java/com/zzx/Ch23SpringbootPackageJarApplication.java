package com.zzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ZZX
 */
@SpringBootApplication
public class Ch23SpringbootPackageJarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Ch23SpringbootPackageJarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Ch23SpringbootPackageJarApplication.class);
    }
}
