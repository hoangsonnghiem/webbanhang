package com.example.demo.Product;

import com.example.demo.CustomResponse;
import com.example.demo.Customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ResponseEntity<Object> getAllProduct();
    ResponseEntity<Object> getProductById(Long pid);
    ResponseEntity<Object> getAllActiveProduct();
    ResponseEntity<Object> getAllProductByCatOrderByDate(Long catid);
    ResponseEntity<Object> updateProductById(Product product, Long pid);
    ResponseEntity<Object> setActiveToFalse(Long pid);
    ResponseEntity<Object> addProduct(Product product);
    CustomResponse getProduct();
}
