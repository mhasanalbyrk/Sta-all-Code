package com.obss.jss.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
@Component
@Order(1)
public class LogFilter implements Filter {
    public static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getMethod().toUpperCase(Locale.ROOT).equals("GET")){
            LOGGER.info("Req of method GET:{}", req.getServletPath());
        }else if (req.getMethod().toUpperCase(Locale.ROOT).equals("POST")){
            LOGGER.info("Req of method POST:{}", req.getServletPath());
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
