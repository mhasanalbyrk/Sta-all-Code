package com.example.firstwebapp.day2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dynamicServlet/*")
public class DynamicServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Request URL:" + req.getRequestURL());
        resp.getWriter().println("Request URI:" + req.getRequestURI());
        resp.getWriter().println("Servlet Path:" + req.getServletPath());
        resp.getWriter().println("Context Path:" + req.getContextPath());
        resp.getWriter().println("Path  Info:" + req.getPathInfo());
        resp.getWriter().println("Query String:" + req.getQueryString());
    }
}
