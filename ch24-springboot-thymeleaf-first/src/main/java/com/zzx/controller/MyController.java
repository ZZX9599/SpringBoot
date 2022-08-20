package com.zzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class MyController {
    @GetMapping("/thymeleaf")
    public String helloThymeLeaf(Model model){
        model.addAttribute("data","thymeleaf模板");
        //指定视图，换成模板
        return "hello";
    }
}
