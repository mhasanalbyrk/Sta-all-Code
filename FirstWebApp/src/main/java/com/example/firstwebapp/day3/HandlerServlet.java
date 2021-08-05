package com.example.firstwebapp.day3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handler-servlet")
public class HandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(req.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
//        req.getAttribute(RequestDispatcher.ERROR_EXCEPTION
    }
}
