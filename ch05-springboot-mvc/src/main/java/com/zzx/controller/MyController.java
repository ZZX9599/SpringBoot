package com.zzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
@RequestMapping("/boot")
public class MyController {
    @ResponseBody
    @RequestMapping(value = {"/hello"})
    public String helloSpringBoot(){
        return "欢迎使用SpringBoot框架";
    }
}
