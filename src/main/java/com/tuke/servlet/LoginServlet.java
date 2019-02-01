package com.tuke.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login doget");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login dopost");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            Session session = subject.getSession();
            System.out.println("sessionId:"+session.getId());
            System.out.println("sessionHost:"+session.getHost());
            System.out.println("sessionTimeout:"+session.getTimeout());
            resp.sendRedirect("seccess.jsp");
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorInfo","用户名和密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
