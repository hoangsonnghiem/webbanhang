package com.example.demo.Customer;

import com.example.demo.CustomResponse;
import com.example.demo.Customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
	ResponseEntity<Object> getAllCustomer();
	ResponseEntity<Object> getCustomerByEmail(String email);
	ResponseEntity<Object> addNewCustomer(Customer customer);
	ResponseEntity<Object> updateCustomerByEmail(Customer customer, String email);
	ResponseEntity<Object> login(String email);
	CustomResponse getCustomer();
}
