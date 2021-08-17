package com.obss.jss.exception;

public class ErrorResponse {
    private String path;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
