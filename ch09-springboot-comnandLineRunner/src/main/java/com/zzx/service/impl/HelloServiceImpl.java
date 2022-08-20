package com.zzx.service.impl;

import com.zzx.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public void say() {
        System.out.println("----你好----");
    }
}
