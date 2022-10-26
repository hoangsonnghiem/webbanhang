package com.example.demo.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.CustomResponse;

import java.util.List;

@Service
public interface CategoryService {
    ResponseEntity<Object> getAllCategory();
    ResponseEntity<Object> getCategoryById(Long catid);
    ResponseEntity<Object> addNewCategory(Category category);
    ResponseEntity<Object> updateCategory(Category category, Long catid);
    CustomResponse getCategory();
}
