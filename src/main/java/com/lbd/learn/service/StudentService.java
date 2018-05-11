package com.lbd.learn.service;

import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;

public interface StudentService {
    /**
     * 根据查询条件，查询学生的分页信息
     * @param searchModel 封装查询条件
     * @param pageNum     查询第几页数据
     * @param pageSize    每页的数据数量
     * @return
     */
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize);
}
