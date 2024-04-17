package com.jay.bookstore.appbymappings;

public class ExceptionResponse {

    String msg;
    String responseCode;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String msg, String responseCode) {
        this.msg = msg;
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}