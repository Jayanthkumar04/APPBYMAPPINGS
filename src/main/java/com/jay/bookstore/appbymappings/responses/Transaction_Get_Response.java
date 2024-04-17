package com.jay.bookstore.appbymappings.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_Get_Response {


    String name;

    long acc_id;

    long transactionRef_id;

    double credit;

    double debit;

    double avlBalance;

    Timestamp lastUpdated;









}
