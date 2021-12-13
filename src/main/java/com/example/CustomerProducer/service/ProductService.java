package com.example.CustomerProducer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CustomerProducer.Exception.ResourceNotFoundException;
import com.example.CustomerProducer.Repository.CustomerRepository;
import com.example.CustomerProducer.model.Customer_Bank;

@Service
@Transactional
public class ProductService {

private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

@Autowired(required=true)
private CustomerRepository customerrepository;

public Customer_Bank PostCustomer(Customer_Bank customer) {
	// TODO Auto-generated method stub
	return customerrepository.save(customer);
}

public List<Customer_Bank> getCustomers() {
	// TODO Auto-generated method stub
	return customerrepository.findAll();
}

public Customer_Bank getCustomersByID(int custid) throws ResourceNotFoundException {
	Customer_Bank cust = customerrepository.findById(custid)
			.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + custid));

	return cust;
}

public String deleteCust(int custid) throws ResourceNotFoundException {
	Customer_Bank cust = customerrepository.findById(custid)
			.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + custid));

	customerrepository.delete(cust);
	return "deleted" + custid;
}

 public ResponseEntity<Customer_Bank> updateCustomer(int custid, Customer_Bank custDetails)
		throws ResourceNotFoundException {
	logger.info("****************Service Class :update customers****************");
	Customer_Bank cust = customerrepository.findById(custid)
			.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + custid));
	cust.setName(custDetails.getName());
	cust.setAddressId(custDetails.getAddressId());
	cust.setAge(custDetails.getAge());
	cust.setTypeofAccount(custDetails.getTypeofAccount());
	final Customer_Bank updatedCustomer = customerrepository.save(cust);

	return ResponseEntity.ok(updatedCustomer);
}

}
