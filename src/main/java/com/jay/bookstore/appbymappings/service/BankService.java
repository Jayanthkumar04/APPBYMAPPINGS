package com.jay.bookstore.appbymappings.service;
import com.jay.bookstore.appbymappings.entity.*;
import com.jay.bookstore.appbymappings.repo.Acc_BalanceRepo;
import com.jay.bookstore.appbymappings.repo.Cust_Acc_MapRepo;
import com.jay.bookstore.appbymappings.repo.Cust_AddressRepo;
import com.jay.bookstore.appbymappings.repo.Cust_DetailsRepo;
import com.jay.bookstore.appbymappings.responses.BalanceResponse;
import com.jay.bookstore.appbymappings.responses.CustomerGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Object putCustomers(Cust_Full_Address cfa)
    {
        Random rm = new Random();

        long cid = rm.nextInt(600000-500000+1)+500000;

        long aid =rm.nextInt(600000-500000+1)+500000;

        long acc_id = rm.nextInt(600000-500000+1)+500000;


        Timestamp currentTimestamp = new Timestamp(new Date().getTime());

        Cust_Address custaddress = new Cust_Address(aid,cfa.getCountry(),cfa.getCity(),cfa.getAddressLine(),cfa.getPin(),currentTimestamp);

        Cust_Details custDeatils = new Cust_Details(cid,cfa.getName(),cfa.getPhone(),cfa.getEmail(),currentTimestamp,currentTimestamp);

        Cust_Acc_Map cam = new Cust_Acc_Map(aid);



        Cust_Address cd =  custAddressRepo.save(custaddress);

        custDeatils.setCustAddress(cd);

        custDetailsRepo.save(custDeatils);



        Cust_Details c = custDetailsRepo.save(custDeatils);

        cam.setCustDetails(c);

        custAccMapRepo.save(cam);


        Acc_Balance accBalance = new Acc_Balance(500);

        Cust_Acc_Map custAccMap =  custAccMapRepo.save(cam);

        accBalance.setCustAccMap(custAccMap);

        accBalanceRepo.save(accBalance);



        accBalanceRepo.save(accBalance);




        return null;

    }

    public Object getCustomers()
    {

        return custDetailsRepo.findAll();
    }

    public Object getCustomerById(long id)
    {
        return custDetailsRepo.findById(id);
    }

    public Object getBalanceBycust_id(long id)
    {
        try{
            Cust_Acc_Map cap=custAccMapRepo.findByCustId(id);
            Optional<Acc_Balance> ab=accBalanceRepo.findById(cap.getAcc_id());
            if(ab.isPresent())
                return new BalanceResponse(cap.getAcc_id(),ab.get().getBalance());
//            else
//                return new ExceptionResponse("account details not found","hct404");
        }
        catch (Exception e){
//            return new ExceptionResponse("customer details not found","hct404");
              return null;
        }

return null;
    }
    public Object getBalanceByacc_id(long acc_id) {
        try {
            Optional<Acc_Balance> ab=accBalanceRepo.findById(acc_id);
            if(ab.isPresent())
                return new BalanceResponse(acc_id,ab.get().getBalance());
//            else
//                return new ExceptionResponse("customer details not found","hct404");
        }
        catch (Exception e){
//            return new ExceptionResponse("customer details not found","hct404");
            return null;
        }

        return null;
    }
}
