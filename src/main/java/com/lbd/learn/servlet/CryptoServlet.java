package com.lbd.learn.servlet;

import com.lbd.learn.util.AESUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CryptoServlet extends HttpServlet {

    private static final long serialVersionUID = -2565431500091806386L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String originalUserName = "";
        String originalPassword = "";
        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            try {
                 originalUserName = AESUtil.aesDecrypt(username);
                 originalPassword = AESUtil.aesDecrypt(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*跳转*/
        req.getRequestDispatcher("/jsp/cryptoLogin.jsp").forward(req, resp);
    }
}
