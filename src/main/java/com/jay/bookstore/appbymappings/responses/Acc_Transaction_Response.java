package com.jay.bookstore.appbymappings.responses;


public class Acc_Transaction_Response {
    long acc_id;
    long to_acc_id;
    String type;
    double amount;
    public Acc_Transaction_Response() {
    }
    public Acc_Transaction_Response(long acc_id, long to_acc_id, String type, double amount) {
        this.acc_id = acc_id;
        this.to_acc_id = to_acc_id;
        this.type = type;
        this.amount = amount;
    }
    public long getAcc_id() {
        return acc_id;
    }
    public void setAcc_id(long acc_id) {
        this.acc_id = acc_id;
    }
    public long getTo_acc_id() {
        return to_acc_id;
    }
    public void setTo_acc_id(long to_acc_id) {
        this.to_acc_id = to_acc_id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }




}
