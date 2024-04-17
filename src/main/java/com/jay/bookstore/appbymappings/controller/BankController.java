package com.jay.bookstore.appbymappings.controller;

import com.jay.bookstore.appbymappings.ExceptionResponse;
import com.jay.bookstore.appbymappings.entity.Cust_Full_Address;
import com.jay.bookstore.appbymappings.exceptions.CustomerNotFoundException;
import com.jay.bookstore.appbymappings.repo.Cust_AddressRepo;
import com.jay.bookstore.appbymappings.repo.Cust_DetailsRepo;
import com.jay.bookstore.appbymappings.responses.Acc_Transaction_Response;
import com.jay.bookstore.appbymappings.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hct")
public class BankController {

@Autowired
    BankService bankService;

    @PostMapping("customers")
    public Object putCustomers(@RequestBody Cust_Full_Address cfa)
    {
        return bankService.putCustomers(cfa);
    }

    @GetMapping("customers")
    public Object getCustomers()
    {
        return bankService.getCustomers();
    }


//    public Object getCustomerById(@PathVariable long id)
//    {
//        return bankService.getCustomerById(id);
//    }
    @GetMapping("customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable long id) {
        try {
            Object customer = bankService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("Provided input's {query params or path params} is/are Invalid!.","HCTB404"));
        }
    }



    @GetMapping("balances")
    public Object getBalance(@RequestParam(required = false) Long cust_id, @RequestParam(required = false) Long acc_id) {
        if(cust_id != null && acc_id!=null)
        {
            return bankService.getBalanceByacc_id(acc_id);
        }
        else if(cust_id!=null)
        {

            return bankService.getBalanceBycust_id(cust_id);
        }
        else if(acc_id != null)
        {
            return bankService.getBalanceByacc_id(acc_id);
        }
        else
        {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Provided input's {query params or path params} is/are Invalid!.","HCTB404"));


        }


    }

    @PostMapping("transactions")
    public Object newTransaction(@RequestBody Acc_Transaction_Response accTran)
    {
        String type = accTran.getType().toLowerCase();

        if(type.equals("credit")) {

            return bankService.newTransaction(accTran);

        }

        else if(type.equals("debit"))
        {
            return bankService.newTransaction(accTran);

        }

        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse("Provided Details are Invalid!..","HCTB400") );
    }

    @GetMapping("transactions")
    public Object getTransactions(@RequestParam(required = false) Long acc_id,@RequestParam(required = false) Long ref_id) {

        if(acc_id == null && ref_id == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse("Required Query parameter's are not provided!.","Hct404"));
        }
        else if(acc_id!=null && ref_id==null) {

            return bankService.getTransactionsByAccId(acc_id);

        }

        else if(ref_id!=null && acc_id==null) {

            return bankService.getTransactionsByRefId(ref_id);

        }

        else if(acc_id !=null && ref_id!=null)
        {
            return bankService.getTransactionsByBoth(ref_id,acc_id);
        }

        else
            return new ExceptionResponse("Provided input's {query params or path params} is/are Invalid!.","HCTB404");


    }






}
