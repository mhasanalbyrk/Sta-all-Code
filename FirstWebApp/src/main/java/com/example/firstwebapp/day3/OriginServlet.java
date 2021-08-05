package com.example.firstwebapp.day3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/origin-servlet")
public class OriginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Info", 1);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("Context", 2);
        if ("forward".equals(req.getParameter("type"))){
            req.getRequestDispatcher("destination-servlet").forward(req,resp);
        }
        else {
            resp.sendRedirect("destination-servlet");
        }
    }
}
