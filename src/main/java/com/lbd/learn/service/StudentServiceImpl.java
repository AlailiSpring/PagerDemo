package com.lbd.learn.service;

import com.lbd.learn.dao.StudentDao;
import com.lbd.learn.dao.StudentDaoImpl;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl() {
        /*创建service实现类，初始化Dao对象*/
        studentDao = new StudentDaoImpl();
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
