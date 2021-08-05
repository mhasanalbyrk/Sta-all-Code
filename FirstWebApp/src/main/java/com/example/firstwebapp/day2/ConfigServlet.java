package com.example.firstwebapp.day2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigServlet extends HttpServlet {
    private String servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletConfig = config.getInitParameter("configValue");
        //String value = config.getInitParameter("configValue");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = "dest2.jsp";
        if ("forward".equals(servletConfig)) {
            req.getRequestDispatcher(s).forward(req, resp);
            return;
        }
        resp.sendRedirect(s);
    }

}

