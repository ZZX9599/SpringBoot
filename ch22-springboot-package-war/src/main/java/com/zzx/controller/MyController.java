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
    @GetMapping("/getData")
    public String myFun(Model model){
        model.addAttribute("data","springboot打包为war文件");
        return "index";
    }
}
