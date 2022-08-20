package com.zzx.service.impl;

import com.zzx.dao.StudentDao;
import com.zzx.domain.Student;
import com.zzx.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentDao dao;

    @Override
    public Student queryStudentById(Integer id) {
        return dao.queryStudentById(id);
    }
}
