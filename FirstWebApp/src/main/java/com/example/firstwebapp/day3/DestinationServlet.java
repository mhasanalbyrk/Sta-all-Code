package com.example.firstwebapp.day3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/destination-servlet")
public class DestinationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().println("In destination");
        ServletContext servletContext = getServletContext();
       // int count = (int) ;
        req.getAttribute("Info");

        resp.getWriter().println("Attr is" + req.getAttribute("Info"));
        resp.getWriter().println("Attr is" + servletContext.getAttribute("Context"));
    }
}
