package com.lbd.learn.servlet;

import com.lbd.learn.model.Constant;
import com.lbd.learn.model.Pager;
import com.lbd.learn.model.Student;
import com.lbd.learn.service.StudentService;
import com.lbd.learn.service.StudentServiceImpl;
import com.lbd.learn.util.StringCheckUtil;
import org.apache.commons.lang3.StringUtils;

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
        String genderStr = req.getParameter("gender");
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int gender = Constant.DEFAULT_GENDER;
        int pageNum = Constant.DEFAULT_PAGENUM;
        int pageSize = Constant.DEFAULT_PAGESIZE;

        if (StringUtils.isNotEmpty(genderStr)) {
            gender = Integer.parseInt(genderStr);
        }
        if (StringUtils.isNotEmpty(pageNumStr)) {
            if(!StringCheckUtil.checkIsNum(pageNumStr)){
                String errorMsg = "参数错误！";
                req.setAttribute("errorMsg", errorMsg);
                /*跳转*/
                req.getRequestDispatcher("/jsp/sublistStudent.jsp").forward(req, resp);
                return;
            }else{
                pageNum = Integer.parseInt(pageNumStr);
            }
        }
        if (StringUtils.isNotEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        Student searchModel = new Student();
        searchModel.setStuName(stuName);
        searchModel.setGender(gender);

        Pager<Student> studentPager = studentService.findStudent(searchModel, pageNum, pageSize);
        req.setAttribute("result", studentPager);
        req.setAttribute("gender",gender);

        /*跳转*/
        req.getRequestDispatcher("/jsp/sublistStudent.jsp").forward(req, resp);
    }
}
