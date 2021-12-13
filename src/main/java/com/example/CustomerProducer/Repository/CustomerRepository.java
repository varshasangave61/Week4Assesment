package com.example.CustomerProducer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CustomerProducer.model.Customer_Bank;

@Repository
public interface CustomerRepository extends JpaRepository<Customer_Bank,Integer>{

}
