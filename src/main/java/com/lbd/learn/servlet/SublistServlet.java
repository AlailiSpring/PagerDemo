package com.lbd.learn.servlet;

import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;
import com.lbd.learn.service.StudentService;
import com.lbd.learn.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SublistServlet extends HttpServlet{

    private static final long serialVersionUID = 8214914943472668786L;

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*接收request里面的参数*/
        String stuName = req.getParameter("stuName");
        int gender = Integer.parseInt(req.getParameter("gender"));
        int pageNum = Integer.parseInt(req.getParameter("pageNum"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        Student searchModel = new Student();
        searchModel.setStuName(stuName);
        searchModel.setGender(gender);

        Pager<Student> studentPager = studentService.findStudent(searchModel, pageNum, pageSize);
        req.setAttribute("result", studentPager);

        /*跳转*/
        req.getRequestDispatcher("/jsp/sublistStudent.jsp").forward(req, resp);
    }
}
