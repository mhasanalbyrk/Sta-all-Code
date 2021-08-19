package com.obss.jss.onlinemarketplace.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(HttpServletRequest req, Exception ex) {

        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(ex.getMessage());
        resp.setPath(req.getRequestURI());

        return resp;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleException(HttpServletRequest req, ProductNotFoundException ex) {

        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(ex.getMessage());
        resp.setPath(req.getRequestURI());

        return resp;
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ErrorResponse handleException(HttpServletRequest req, UsernameExistsException ex) {

        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(ex.getMessage());
        resp.setPath(req.getRequestURI());

        return resp;
    }
}
