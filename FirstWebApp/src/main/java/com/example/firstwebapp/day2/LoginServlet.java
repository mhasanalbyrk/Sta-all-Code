package com.example.firstwebapp.day2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspFile = "WEB-INF/page_1.jsp";
        req.getRequestDispatcher(jspFile).forward(req, resp);
        System.out.printf(req.getRemoteUser());
        resp.getWriter().println(req.getRemoteUser());
       // out.print(req.getRemoteUser());
    }
}
