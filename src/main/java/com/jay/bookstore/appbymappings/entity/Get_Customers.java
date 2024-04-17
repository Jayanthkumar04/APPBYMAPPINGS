package com.jay.bookstore.appbymappings.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Get_Customers {

    String name;

    long phone;

    String email;

    @Id
    long customer_id;


    long account_id;

    Timestamp createdOn;

}
