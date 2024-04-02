package com.jay.bookstore.appbymappings.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Acc_Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int duplicateColumn;
    double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_id")
    Cust_Acc_Map custAccMap;


    public Acc_Balance() {

    }

    public Acc_Balance(double balance) {
        this.balance = balance;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cust_Acc_Map getCustAccMap() {
        return custAccMap;
    }

    public void setCustAccMap(Cust_Acc_Map custAccMap) {
        this.custAccMap = custAccMap;
    }
}
