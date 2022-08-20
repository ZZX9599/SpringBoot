package com.zzx.dao;

import com.zzx.domain.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @Mapper表示告诉 Mybatis这个是dao接口，创建这个接口的代理对象
 */
public interface StudentDao {
    /**
     * 根据id查学生
     * @param id
     * @return
     */
    Student queryStudentById(@Param("stuId") Integer id);
}
