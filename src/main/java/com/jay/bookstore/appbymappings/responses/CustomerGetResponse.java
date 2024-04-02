package com.jay.bookstore.appbymappings.responses;

import java.sql.Timestamp;

public class CustomerGetResponse {

    String name;

    long phone;

    String mail;

    long c_id;

    long a_id;

    Timestamp created_on;

    public CustomerGetResponse() {
    }

    public CustomerGetResponse(String name, long phone, String mail, long c_id, long a_id, Timestamp created_on) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.c_id = c_id;
        this.a_id = a_id;
        this.created_on = created_on;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getC_id() {
        return c_id;
    }

    public void setC_id(long c_id) {
        this.c_id = c_id;
    }

    public long getA_id() {
        return a_id;
    }

    public void setA_id(long a_id) {
        this.a_id = a_id;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
