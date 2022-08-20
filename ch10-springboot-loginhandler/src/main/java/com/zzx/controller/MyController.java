package com.zzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class MyController {
    @RequestMapping("/user/account")
    @ResponseBody
    public String userAccount(){
        return "访问user/account";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public String userLogin(){
        return "访问user/login";
    }
}
