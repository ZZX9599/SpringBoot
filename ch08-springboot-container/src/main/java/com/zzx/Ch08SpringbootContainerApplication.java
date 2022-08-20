package com.zzx;

import com.zzx.service.UserService;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZZX
 */
@SpringBootApplication
public class Ch08SpringbootContainerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx=SpringApplication.run(Ch08SpringbootContainerApplication.class, args);
        UserService service=(UserService) ctx.getBean("userService");
        service.show();
    }

}
