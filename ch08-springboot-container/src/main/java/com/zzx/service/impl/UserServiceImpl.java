package com.zzx.service.impl;

import com.zzx.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void show() {
        System.out.println("你好");
    }
}
