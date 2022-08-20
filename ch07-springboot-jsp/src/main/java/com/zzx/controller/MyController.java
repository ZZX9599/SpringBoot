package com.zzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
@RequestMapping
public class MyController {
    @RequestMapping("/data")
    public String getData(HttpServletRequest request){
        request.setAttribute("data","欢迎使用SpringBoot做jsp!");
        return "index";
    }
}
