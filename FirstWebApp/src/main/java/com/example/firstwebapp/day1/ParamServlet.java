package com.example.firstwebapp.day1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/param-servlet")
public class ParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");


        if (name == null){
            resp.getWriter().println("No parameter used");
            return;
        }

        resp.getWriter().println("Parameter used" + name);
       // resp.getWriter().println("Parameter used" + surname);

    }
}
