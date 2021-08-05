package com.example.firstwebapp.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exception-servlet")
public class ExceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("5xx".equals(req.getParameter("error"))){
//            req.setAttribute("error", );
            resp.getWriter().println(3/0);


        }
        else{
            resp.sendError(404);
        }
    }
}
