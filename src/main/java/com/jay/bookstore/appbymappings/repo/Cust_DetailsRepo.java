package com.jay.bookstore.appbymappings.repo;

import com.jay.bookstore.appbymappings.entity.Cust_Address;
import com.jay.bookstore.appbymappings.entity.Cust_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cust_DetailsRepo extends JpaRepository<Cust_Details,Long> {


}
