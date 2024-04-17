package com.jay.bookstore.appbymappings.repo;

import com.jay.bookstore.appbymappings.entity.Get_Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Get_CustomersRepo extends JpaRepository<Get_Customers,Long> {


}
