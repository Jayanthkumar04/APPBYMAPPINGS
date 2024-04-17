package com.jay.bookstore.appbymappings.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class Acc_Transactions {

    @Id
    long transaction_id;
    long transactionRef_id;

    double credit;

    double debit;

    double avlBalance;

    Timestamp lastUpdated;

    long cust_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_id")

    Cust_Acc_Map custAccMap;

    public Acc_Transactions() {

    }

    public Acc_Transactions(long transaction_id, long transactionRef_id, double credit, double debit, double avlBalance, Timestamp lastUpdated,long cust_id) {
        this.transaction_id = transaction_id;
        this.transactionRef_id = transactionRef_id;
        this.credit = credit;
        this.debit = debit;
        this.avlBalance = avlBalance;
        this.lastUpdated = lastUpdated;
        this.cust_id = cust_id;

    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public long getTransactionRef_id() {
        return transactionRef_id;
    }

    public void setTransactionRef_id(long transactionRef_id) {
        this.transactionRef_id = transactionRef_id;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getAvlBalance() {
        return avlBalance;
    }

    public void setAvlBalance(double avlBalance) {
        this.avlBalance = avlBalance;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Cust_Acc_Map getCustAccMap() {
        return custAccMap;
    }

    public void setCustAccMap(Cust_Acc_Map custAccMap) {
        this.custAccMap = custAccMap;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }
}
