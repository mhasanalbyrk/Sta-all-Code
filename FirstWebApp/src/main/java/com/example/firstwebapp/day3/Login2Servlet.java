package com.example.firstwebapp.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
    private String username = "hasan";
    private String password = "1234";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (username.equals(req.getParameter("username")) && password.equals(req.getParameter("password"))){
            HttpSession session = req.getSession();
            session.setAttribute("isUserLoggedIn", "true");
            resp.sendRedirect(req.getContextPath() + "/private/secured.jsp");

        }else {
            resp.getWriter().println("Username or password is invalid");
        }
    }
}
