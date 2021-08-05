package com.example.firstwebapp.day4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create-session")
public class CreateSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("Key1", "value1");
        if (req.getParameter("method" ) != null){
            req.getRequestDispatcher("list-session-attributes").forward(req, resp);
        }
        else {
            resp.sendRedirect("list-session-attributes");
        }

    }
}
