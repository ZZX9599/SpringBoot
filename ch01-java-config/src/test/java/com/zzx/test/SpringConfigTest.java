package com.zzx.test;


import com.zzx.config.SpringConfig;
import com.zzx.vo.Mysql;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public class SpringConfigTest {
    public static void main(String[] args) {
        //使用注解的话，就使用AnnotationConfigApplicationContext实现类
        //配置文件的话，就使用ClassPathXmlApplicationContext实现类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        //使用的是方法名来获取对象
        Date date=(Date) ctx.getBean("date");
        System.out.println(date);
        ArrayList list=(ArrayList) ctx.getBean("arrayList");
        System.out.println(list);
        Mysql mysql=(Mysql) ctx.getBean("mysql");
        System.out.println(mysql);
    }
}
