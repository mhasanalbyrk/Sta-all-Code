package com.example.firstwebapp.day4;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/private/*")
public class FilterPrivate implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if ( session != null &&session.getAttribute("isUserLoggedIn") != null){

            filterChain.doFilter(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/login2.jsp");
        }
    }
}
