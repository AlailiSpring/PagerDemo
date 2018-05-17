package com.lbd.learn.dao;

import com.lbd.learn.model.Constant;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateStudentDaoImpl implements StudentDao {
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        Pager<Student> result = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append("from Student where 1=1 ");
        countHql.append("select count(id) from Student where 1=1");
        if (StringUtils.isNotEmpty(stuName)) {
            hql.append(" and stuName like :stuName ");
            countHql.append(" and stuName like :stuName ");
            paramMap.put("stuName", "%" + stuName.trim() + "%");
        }
        if (gender == Constant.GENDER_FAMALE || gender == Constant.GENDER_MALE) {
            hql.append(" and gender =:gender ");
            countHql.append(" and gender=:gender ");
            paramMap.put("gender", gender);
        }

        int fromIndex = pageSize * (pageNum - 1);
        hql.append(" limit " + fromIndex + " , " + pageSize);

        Session session = null;

        List<Student> studentList = new ArrayList<Student>();
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        Query hqlQuery = session.createQuery(hql.toString());
        Query countHqlQuery = session.createQuery(countHql.toString());

            /*设置查询参数*/
        setQueryParams(hqlQuery, paramMap);
        setQueryParams(countHqlQuery, paramMap);

        hqlQuery.setFirstResult(fromIndex);
        hqlQuery.setMaxResults(pageSize);

        studentList = hqlQuery.list();

        List<?> countResult = countHqlQuery.list();
        int totalRecord = ((Number) countResult.get(0)).intValue();

            /*获取总页数*/
        int totalPage = totalRecord / pageSize;
        if (totalRecord % pageSize != 0) {
            totalPage = totalPage + 1;
        }

        result = new Pager<Student>(pageNum, totalPage, pageSize, totalRecord, studentList);


        if (session != null) {
            sessionFactory.close();
        }

        return result;
    }

    /**
     * 设置查询的参数
     *
     * @param query
     * @param paramMap
     * @return
     */
    private Query setQueryParams(Query query, Map<String, Object> paramMap) {
        if (paramMap != null && !paramMap.isEmpty()) {
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query;
    }
}
