package com.jay.bookstore.appbymappings.responses;

public class CustResponse {

    String name;
    long customer_id;

    long acoount_id;

    double balance;

    public CustResponse() {
    }

    public CustResponse(String name, long customer_id, long acoount_id,double balance) {
        this.name = name;
        this.customer_id = customer_id;
        this.acoount_id = acoount_id;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getAcoount_id() {
        return acoount_id;
    }

    public void setAcoount_id(long acoount_id) {
        this.acoount_id = acoount_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
