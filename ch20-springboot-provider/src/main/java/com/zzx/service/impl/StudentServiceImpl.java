package com.zzx.service.impl;

import com.zzx.domain.Student;
import com.zzx.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * 使用注解暴露服务,注明接口和版本
 */
@Component
@DubboService(interfaceClass = StudentService.class,version = "1.0.0")
public class StudentServiceImpl implements StudentService {
    @Override
    public Student queryStudentById(Integer id) {
        Student student=new Student();

        if(id==1001){
            student=new Student(1001,"周志雄",20);
        }else if(id==1002){
            student=new Student(1002,"蒋雪丽",23);
        }

        System.out.println("服务者被调用！");
        return student;
    }
}
