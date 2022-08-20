package com.zzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class MyController {

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        mv.addObject("data","springboot打包jar");
        return mv;
    }
}
