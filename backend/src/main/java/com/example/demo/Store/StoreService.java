package com.example.demo.Store;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {
    ResponseEntity<Object> getAllStoreList();
    ResponseEntity<Object> getStoreById(Long storeid);
    ResponseEntity<?> getActiveStore();
    ResponseEntity<?> addStore(Store store);
    ResponseEntity<?> updateStore (Store store, Long storeid);
    ResponseEntity<?> deleteStore (Long storeid);
}
