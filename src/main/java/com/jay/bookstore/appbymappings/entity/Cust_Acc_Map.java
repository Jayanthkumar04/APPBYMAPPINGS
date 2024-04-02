package com.jay.bookstore.appbymappings.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Cust_Acc_Map {
@Id
long acc_id;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="cust_id")
Cust_Details custDetails ;

    public Cust_Acc_Map() {
    }

    public Cust_Acc_Map(long acc_id) {
        this.acc_id = acc_id;
    }

    public Long getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(long acc_id) {
        this.acc_id = acc_id;
    }

    public Cust_Details getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(Cust_Details custDetails) {
        this.custDetails = custDetails;
    }
}
