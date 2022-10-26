package com.example.demo.ReceiptList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;

@Service
public interface ReceiptListService {

	ResponseEntity<Object> getAllReceiptList();
	ResponseEntity<Object> getAllReceiptListById(Long rid);
	ResponseEntity<Object> addNewReceiptList(ReceiptList receiptList);
	ResponseEntity<Object> UpdateReceiptList(ReceiptList receiptList, Long rid);
	CustomResponse getReceiptList();
}
