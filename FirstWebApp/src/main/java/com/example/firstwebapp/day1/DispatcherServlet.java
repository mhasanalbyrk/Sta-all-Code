package com.example.firstwebapp.day1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
@WebServlet("/dispatcher-servlet")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("");
        String jspFile = req.getParameter("destination");
        String dispatch = req.getParameter("dispatch");
        System.out.println("sgsfgdfgdfgdfgdf");
        if ("redirect".equals(dispatch)){
            resp.sendRedirect(jspFile);
            return;
        }

        req.getRequestDispatcher(jspFile).forward(req, resp);

    }
}
