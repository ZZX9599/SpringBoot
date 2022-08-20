package com.zzx.service;

import com.zzx.domain.Student;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public interface StudentService {
    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    Student queryStudentById(Integer id);
}
