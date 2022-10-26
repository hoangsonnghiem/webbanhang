package com.example.demo.Receipt;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;

@Service
public interface ReceiptService {

	ResponseEntity<Object> getAllReceipt();
	ResponseEntity<Object> getAllReceiptById(Long rid);
	ResponseEntity<Object> addNewReceipt(Receipt receipt);
	ResponseEntity<Object> UpdateReceipt(Receipt receipt, Long rid);
	CustomResponse getReceipt();
}
