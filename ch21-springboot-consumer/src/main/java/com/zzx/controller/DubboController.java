package com.zzx.controller;

import com.zzx.domain.Student;
import com.zzx.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@RestController
public class DubboController {
    @DubboReference(interfaceClass = StudentService.class,version = "1.0.0")
    private StudentService service;

    @GetMapping("/query")
    public String queryStudent(){
        Student student=service.queryStudentById(1001);
        return "dubbo获取的学生信息"+student;
    }
}
