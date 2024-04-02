package com.jay.bookstore.appbymappings.repo;

import com.jay.bookstore.appbymappings.entity.Cust_Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cust_AddressRepo extends JpaRepository<Cust_Address,Long> {


}
