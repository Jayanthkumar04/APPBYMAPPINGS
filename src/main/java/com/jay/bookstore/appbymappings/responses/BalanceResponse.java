package com.jay.bookstore.appbymappings.responses;

public class BalanceResponse {

    long account_id;

    double aval_balance;

    public BalanceResponse() {
    }

    public BalanceResponse(long account_id, double aval_balance) {
        this.account_id = account_id;
        this.aval_balance = aval_balance;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public double getAval_balance() {
        return aval_balance;
    }

    public void setAval_balance(double aval_balance) {
        this.aval_balance = aval_balance;
    }
}
