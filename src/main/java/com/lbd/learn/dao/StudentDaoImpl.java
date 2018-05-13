package com.lbd.learn.dao;

import com.lbd.learn.model.Constant;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;
import com.lbd.learn.util.JdbcUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        List<Student> studentList = getAllStudent(searchModel);
        Pager<Student> studentPager = new Pager<Student>(pageSize, pageNum, studentList);
        return studentPager;
    }

    public List<Student> getAllStudent(Student searchModel) {
        List<Student> result = new ArrayList<Student>();
        List<Object> paramList = new ArrayList<Object>();
        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_student where 1=1 ");
        if (StringUtils.isNotEmpty(stuName)) {
            sql.append(" and stu_name like ? ");
            paramList.add("%"+stuName.trim()+"%");
        }
        if (gender == Constant.GENDER_FAMALE || gender == Constant.GENDER_MALE) {
            sql.append(" and gender=? ");
            paramList.add(gender);
        }

        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString().toUpperCase(), paramList);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> item : mapList) {
                    Student student = new Student(item);
                    result.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据出错", e);
        }finally {
            if (jdbcUtil != null) {
                jdbcUtil.releaseConn();
            }
        }
        return result;
    }
}
