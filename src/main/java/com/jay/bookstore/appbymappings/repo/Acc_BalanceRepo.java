package com.jay.bookstore.appbymappings.repo;

import com.jay.bookstore.appbymappings.entity.Acc_Balance;
import com.jay.bookstore.appbymappings.entity.Cust_Acc_Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Acc_BalanceRepo extends JpaRepository<Acc_Balance,Long> {
    ;

    @Query(value = "select * from Cust_Acc_Map cam where cam.cust_id=:id " ,nativeQuery = true)
    Cust_Acc_Map findByCustId(long id);


}
