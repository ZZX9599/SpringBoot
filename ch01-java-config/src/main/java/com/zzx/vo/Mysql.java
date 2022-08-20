package com.zzx.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Component("mysql")
public class Mysql {
    @Value("${jdbc.url}")
    private String url;
    @Value("jdbc.username")
    private String userName;
    @Value("${jdbc.password}")
    private String password;

    @Override
    public String toString() {
        return "Mysql{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
