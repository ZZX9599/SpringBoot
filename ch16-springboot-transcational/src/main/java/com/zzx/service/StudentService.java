package com.zzx.service;

import com.zzx.domain.Student;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public interface StudentService {
    /**
     * 添加学生
     * @param student
     * @return
     */
    int addStudent(Student student);
}
