package com.zzx;

import com.zzx.service.HelloService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@SpringBootApplication
public class Application implements ApplicationRunner{
    @Resource
    private HelloService service;
    public static void main(String[] args) {
        System.out.println("1:创建容器");
        ApplicationContext ctx=SpringApplication.run(Application.class,args);
        System.out.println("3:创建容器之后");
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("2:创建好容器之后执行的方法");
        //在容器创建好后执行的方法，可以读取配置文件，连接数据库等
        service.say();
    }
}
