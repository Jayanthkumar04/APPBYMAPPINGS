package com.jay.bookstore.appbymappings.controller;

import com.jay.bookstore.appbymappings.entity.Cust_Full_Address;
import com.jay.bookstore.appbymappings.repo.Cust_AddressRepo;
import com.jay.bookstore.appbymappings.repo.Cust_DetailsRepo;
import com.jay.bookstore.appbymappings.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("customers/{id}")
    public Object getCustomerById(@PathVariable long id)
    {
        return bankService.getCustomerById(id);
    }

    @GetMapping("balances")
    public Object getBalance(@RequestParam long cust_id, @RequestParam long acc_id) {

        if(cust_id !=0 && acc_id!=0)
        {
            return bankService.getBalanceBycust_id(cust_id);
        }
        else if(cust_id!=0)
        {

            return bankService.getBalanceBycust_id(cust_id);
        }
        else if(acc_id != 0)
        {
            return bankService.getBalanceByacc_id(acc_id);
        }
        else
        {
//            return new ExceptionResponse("Required Query parameter's are not provided!.","HCTB400");
        return null;
        }


    }


}
