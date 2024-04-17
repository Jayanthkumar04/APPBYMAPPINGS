package com.jay.bookstore.appbymappings.repo;

import com.jay.bookstore.appbymappings.entity.Acc_Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Acc_TransactionsRepo extends JpaRepository<Acc_Transactions,Long> {

;
@Query(value="select *from Acc_Transactions ac where ac.acc_id=:id",nativeQuery = true)
List<Acc_Transactions> findByAcc_id(long id);


@Query(value = "select *from Acc_Transactions ac where ac.transaction_ref_id=:refid", nativeQuery = true)
List<Acc_Transactions> findByRef_id(long refid);

@Query(value = "select *from Acc_Transactions ac where ac.transaction_ref_id=:refid and ac.acc_id=:id", nativeQuery = true)
Optional<Acc_Transactions> findByBoth(long refid, long id);




}
