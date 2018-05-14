package com.lbd.learn.service;

import com.lbd.learn.dao.JdbcSqlStudentDaoImpl;
import com.lbd.learn.dao.StudentDao;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;

public class JdbcSqlStudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public JdbcSqlStudentServiceImpl() {
        studentDao = new JdbcSqlStudentDaoImpl();
    }
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        return studentDao.findStudent(searchModel,pageNum,pageSize);
    }
}
