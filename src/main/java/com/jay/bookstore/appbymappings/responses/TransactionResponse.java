package com.jay.bookstore.appbymappings.responses;

public class TransactionResponse {

    String msg;
    String statusCode;

    long refid;

    public TransactionResponse() {
    }

    public TransactionResponse(String msg, String statusCode, long refid) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.refid = refid;
    }

    public TransactionResponse(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public long getRefid() {
        return refid;
    }

    public void setRefid(long refid) {
        this.refid = refid;
    }
}
