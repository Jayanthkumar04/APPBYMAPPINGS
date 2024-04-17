package com.jay.bookstore.appbymappings.service;
import com.jay.bookstore.appbymappings.ExceptionResponse;
import com.jay.bookstore.appbymappings.entity.*;
import com.jay.bookstore.appbymappings.exceptions.CustomerNotFoundException;
import com.jay.bookstore.appbymappings.repo.*;
import com.jay.bookstore.appbymappings.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BankService {
    @Autowired
    Cust_AddressRepo custAddressRepo;

    @Autowired
    Cust_DetailsRepo custDetailsRepo;

    @Autowired
    Cust_Acc_MapRepo custAccMapRepo;

    @Autowired
    Acc_BalanceRepo accBalanceRepo;

    @Autowired
    Acc_TransactionsRepo accTransactionsRepo;

    @Autowired
    Get_CustomersRepo getCustomersRepo;

    public Object putCustomers(Cust_Full_Address cfa)
    {
        Random rm = new Random();

        long cid = rm.nextInt(1000000-100000+1)+100000;

        long aid =rm.nextInt(1000000-100000+1)+100000;

        long acc_id = rm.nextInt(1000000-100000+1)+100000;


        Timestamp currentTimestamp = new Timestamp(new Date().getTime());

        Cust_Address custaddress = new Cust_Address(aid,cfa.getCountry(),cfa.getCity(),cfa.getAddressLine(),cfa.getPin(),currentTimestamp);

        Cust_Details custDeatils = new Cust_Details(cid,cfa.getName(),cfa.getPhone(),cfa.getEmail(),currentTimestamp,currentTimestamp);

        Cust_Address cd =  custAddressRepo.save(custaddress);
        custDeatils.setCustAddress(cd);
        custDetailsRepo.save(custDeatils);


        Cust_Acc_Map cam = new Cust_Acc_Map(aid);

        Cust_Details c = custDetailsRepo.save(custDeatils);

        cam.setCustDetails(c);

        custAccMapRepo.save(cam);


        Acc_Balance accBalance = new Acc_Balance(500);

        Cust_Acc_Map custAccMap =  custAccMapRepo.save(cam);

        accBalance.setCustAccMap(custAccMap);

        accBalanceRepo.save(accBalance);


        Get_Customers getCustomers = new Get_Customers(cfa.getName(),cfa.getPhone(), cfa.getEmail(), cid,acc_id,currentTimestamp);

       getCustomersRepo.save(getCustomers);

        return new CustResponse(cfa.getName(), cid,acc_id,accBalance.getBalance());

    }

    public Object getCustomers()
    {
        List<Cust_Details> lst = custDetailsRepo.findAll();

        if(lst.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else {
            return getCustomersRepo.findAll();
        }
        }

    public Object getCustomerById(long id)
    {
        Optional<Get_Customers> customer2 = getCustomersRepo.findById(id);

        if(customer2.isPresent())
        return customer2.get();

        else
        {
            throw new CustomerNotFoundException("Customer not found for id: " + id, "HCTB404");
        }



    }



    public Object getBalanceBycust_id(long id)
    {
        try{
            Cust_Acc_Map cap=custAccMapRepo.findByCustId(id);
            Optional<Acc_Balance> ab= Optional.ofNullable(accBalanceRepo.findByacc_id(cap.getAcc_id()));
            if(ab.isPresent())
                return new BalanceResponse(cap.getAcc_id(),ab.get().getBalance());
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ExceptionResponse("customer details not found", "hct404"));
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("customer details not found", "hct404"));

        }


    }


    public Object getBalanceByacc_id(long acc_id) {
        try {
            Optional<Acc_Balance> ab= Optional.ofNullable(accBalanceRepo.findByacc_id(acc_id));
            if(ab.isPresent())
                return new BalanceResponse(acc_id,ab.get().getBalance());
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ExceptionResponse("customer details not found", "hct404"));
            }
            }
        catch (Exception e){

            return new ExceptionResponse("customer details not found","hct404");
        }

    }

    public Object newTransaction(Acc_Transaction_Response accTran)
    {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        long acc_id = accTran.getAcc_id();

        long to_acc_id= accTran.getTo_acc_id();

        Long cust_id1 = custAccMapRepo.getCustId(acc_id);
        Long cust_id2 = custAccMapRepo.getCustId(to_acc_id);
        double amount = accTran.getAmount();
        Random rm = new Random();

        long transac_id1=rm.nextInt(1000000-100000+1)+100000;

        long transac_id2=rm.nextInt(1000000-100000+1)+100000;

        long ref_id=rm.nextInt(1000000-100000+1)+100000;


        if (cust_id1 == null || cust_id2 == null) {
            // If either sender or receiver details are incorrect, return bad request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Sender or receiver details are incorrect.", "HCTB401"));
        }

        Optional<Acc_Balance> sender = Optional.ofNullable(accBalanceRepo.findByacc_id(acc_id));
        Optional<Acc_Balance> receiver = Optional.ofNullable(accBalanceRepo.findByacc_id(to_acc_id));

        if(sender.isPresent() && receiver.isPresent())
        {
            Acc_Balance transactionAmount = sender.get();

            double accBalance = transactionAmount.getBalance();
            if(accBalance >= amount)
            {
                transactionAmount.setBalance(accBalance-amount);

                Acc_Transactions accTransactions = new Acc_Transactions(transac_id1,ref_id,0,amount,transactionAmount.getBalance(),currentTime,cust_id1);

                Cust_Acc_Map custAccMap = new Cust_Acc_Map(acc_id);

                Cust_Details custDetails = new Cust_Details();
                custDetails.setCust_id(cust_id1); // Set cust_id for sender
                custAccMap.setCustDetails(custDetails);

                accTransactions.setCustAccMap(custAccMap);

                accTransactionsRepo.save(accTransactions);

                accBalanceRepo.save(transactionAmount);


            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionResponse("Insufficient balance in sender's account","hct400",ref_id));
            }

            Acc_Balance transactionAmount2 = receiver.get();
            double accBalance2 = transactionAmount2.getBalance();

            transactionAmount2.setBalance(accBalance2+amount);

            Acc_Transactions accTransactions = new Acc_Transactions(transac_id2,ref_id,amount,0,transactionAmount2.getBalance(),currentTime,cust_id2);

            Cust_Acc_Map custAccMap = new Cust_Acc_Map(to_acc_id);

            Cust_Details custDetails = new Cust_Details();
            custDetails.setCust_id(cust_id2); // Set cust_id for sender
            custAccMap.setCustDetails(custDetails);

            accTransactions.setCustAccMap(custAccMap);
            accTransactionsRepo.save(accTransactions);
            accBalanceRepo.save(transactionAmount2);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse("Provided Details are Invalid!..","HCTB400"));
        }



        return new TransactionResponse("Transaction Sucessfull","HCT200",ref_id);
    }

    public Object  getTransactionsByAccId(long acc_id)
    {

        try {
            List<Acc_Transactions> transaction = accTransactionsRepo.findByAcc_id(acc_id);

            if(transaction.size()>0) {


                return transaction;

//                return new Transaction_Get_Response(cust.get().getName(),acc_id,transaction.get().getTransactionRef_id(),transaction.get().getCredit(),transaction.get().getDebit(),transaction.get().getAvlBalance(),transaction.get().getLastUpdated());
            }
                else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ExceptionResponse("customer details not found", "hct404"));

            }
        }
        catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ExceptionResponse("customer details not found", "hct404"));

        }
//        return accTransactionsRepo.findByAcc_id(acc_id);

    }

    public Object  getTransactionsByRefId(long ref_id) {

        List<Acc_Transactions> accTrans =  accTransactionsRepo.findByRef_id(ref_id);

        if(accTrans.size() > 0)
        {
            return accTrans;
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse("customer details not found", "hct404") );
        }


    }

    public Object getTransactionsByBoth(long ref_id,long acc_id)
    {
        Optional<Acc_Transactions> accTrans =  accTransactionsRepo.findByBoth(ref_id,acc_id);

        if(accTrans.isPresent())
        {
            return accTrans;
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse("customer details not found", "hct404") );
        }

    }





}

