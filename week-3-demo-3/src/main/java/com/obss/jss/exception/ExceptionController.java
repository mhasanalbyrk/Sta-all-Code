package com.obss.jss.exception;

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

    @ExceptionHandler(BaseException.class)
    public ErrorResponse handleException(HttpServletRequest req, BaseException ex) {

        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(ex.getMessage());
        resp.setPath(req.getRequestURI());

        return resp;
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ErrorResponse handleException(HttpServletRequest req, NameNotFoundException ex) {

        ErrorResponse resp = new ErrorResponse();
        resp.setMessage(ex.getMessage());
        resp.setPath(req.getRequestURI());

        return resp;
    }
}
