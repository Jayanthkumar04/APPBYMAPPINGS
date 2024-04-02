package com.jay.bookstore.appbymappings.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class Cust_Details {

    @Id
    long cust_id;

    String name;

    long phone;

    String email;

    Timestamp created;

    Timestamp lastUpdated;

    @OneToOne
    @JoinColumn(name="address_id")
    Cust_Address custAddress;

    public Cust_Details() {
    }

    public Cust_Details(long cust_id, String name, long phone, String email, Timestamp created, Timestamp lastUpdated) {
        this.cust_id = cust_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.lastUpdated = lastUpdated;

    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Cust_Address getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(Cust_Address custAddress) {
        this.custAddress = custAddress;
    }
}
