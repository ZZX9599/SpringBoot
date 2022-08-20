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
    StudentService service;

    @RequestMapping("/student/queryById")
    @ResponseBody
    public String queryStudent(Integer id){
        Student student=service.queryStudentById(id);
        return student.toString();
    }
}
