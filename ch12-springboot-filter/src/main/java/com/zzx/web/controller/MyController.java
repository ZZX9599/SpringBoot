package com.zzx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class MyController {
    @RequestMapping("/user/login")
    @ResponseBody
    public String userLogin(){
        return "执行过滤器";
    }

    @RequestMapping("/good/add")
    @ResponseBody
    public String userAdd(){
        return "不执行过滤器";
    }
}
