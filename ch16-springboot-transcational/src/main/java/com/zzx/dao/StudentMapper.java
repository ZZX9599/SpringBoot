package com.zzx.dao;

import com.zzx.domain.Student;

public interface StudentMapper {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入学生信息
     * @param record
     * @return
     */
    int insert(Student record);


    int insertSelective(Student record);

    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(Student record);
}