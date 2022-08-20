package com.zzx.controller;

import com.zzx.vo.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class HelloController {
    @Value("${student.name}")
    private String name;
    @Value("${student.age}")
    private Integer age;
    @Resource
    private Student student;

    @ResponseBody
    @RequestMapping("/data")
    public String queryData(){
        return "姓名:"+name+"年龄:"+age+"学生对象"+student;
    }
}
