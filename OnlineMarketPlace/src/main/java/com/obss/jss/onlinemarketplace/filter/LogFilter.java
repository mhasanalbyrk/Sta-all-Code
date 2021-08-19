package com.obss.jss.onlinemarketplace.filter;

import com.obss.jss.onlinemarketplace.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(1)
public class LogFilter implements Filter {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        LOGGER.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        filterChain.doFilter(req, res);
        LOGGER.info(
                "Logging Response :{}",
                res.getContentType());
    }
}
