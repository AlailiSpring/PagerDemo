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

/**
 * 使用mysqlS数据库limit关键字实现分页
 */
public class JdbcSqlStudentDaoImpl implements StudentDao {
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        Pager<Student> result = null;
        List<Object> paramList = new ArrayList<Object>();
        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        sql.append("select * from t_student where 1=1 ");
        countSql.append("select count(id) as totalRecord from t_student where 1=1");
        if (StringUtils.isNotEmpty(stuName)) {
            sql.append(" and stu_name like ? ");
            countSql.append(" and stu_name like ? ");
            paramList.add("%"+stuName.trim()+"%");
        }
        if (gender == Constant.GENDER_FAMALE || gender == Constant.GENDER_MALE) {
            sql.append(" and gender=? ");
            countSql.append(" and gender=? ");
            paramList.add(gender);
        }

        int fromIndex = pageSize * (pageNum - 1);
        sql.append(" limit " + fromIndex + " , " + pageSize);

        JdbcUtil jdbcUtil = null;
        List<Student> studentList = new ArrayList<Student>();
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);
            /*总记录数*/
            int totalRecord = ((Number)countMap.get("totalRecord")).intValue();
            /*获取总页数*/
            int totalPage = totalRecord / pageSize;
            if(totalRecord % pageSize !=0){
                totalPage = totalPage + 1;
            }
            List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString().toUpperCase(), paramList);
            if (mapList != null && mapList.size() > 0) {
                for (Map<String, Object> item : mapList) {
                    Student student = new Student(item);
                    studentList.add(student);
                }
            }
            /*组织Pager对象*/
            result = new Pager<Student>(pageNum,totalPage,pageSize,totalRecord,studentList);
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
