package com.zzx.controller;

import com.zzx.domain.Student;
import com.zzx.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
public class StudentController {
    @Resource
    private StudentService service;
    @ResponseBody
    @RequestMapping("/addStudent")
    public String addStudent(String name,Integer age){
        Student student=new Student();
        student.setName(name);
        student.setAge(age);
        int rows=service.addStudent(student);
        return "添加学生记录数量"+rows;
    }
}
