package com.example.demo.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;
@Service
public interface AdminService {
    ResponseEntity<Object> getAllAdmin();
    ResponseEntity<Object> getAllAdminByEmail(String email);
    ResponseEntity<Object> addNewAdmin(Admin admin);
	ResponseEntity<Object> getAdminByEmail(String Email);
	ResponseEntity<Object> updateAdmin(Admin admin, String email);
	CustomResponse getAdmin();
}