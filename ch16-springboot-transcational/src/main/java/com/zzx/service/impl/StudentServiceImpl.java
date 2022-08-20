package com.zzx.service.impl;

import com.zzx.dao.StudentMapper;
import com.zzx.domain.Student;
import com.zzx.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper mapper;

    /**
     * 默认的隔离级别ISOLATION-DEFAULT , 传播行为REQUIRED , 超时时间 -1
     * @param student
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public int addStudent(Student student) {
        System.out.println("业务方法add执行");
        int rows=mapper.insert(student);
        System.out.println("执行了sql语句");
        //抛出运行异常，回滚事务
        return rows;
    }
}
