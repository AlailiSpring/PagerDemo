package com.lbd.learn.service;

import com.lbd.learn.dao.StudentDao;
import com.lbd.learn.dao.SublistStudentDaoImpl;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;

public class SublistStudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public SublistStudentServiceImpl() {
        /*创建service实现类，初始化Dao对象*/
        studentDao = new SublistStudentDaoImpl();
    }
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        return studentDao.findStudent(searchModel, pageNum, pageSize);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
