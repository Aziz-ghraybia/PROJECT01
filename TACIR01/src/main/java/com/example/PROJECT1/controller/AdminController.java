package com.example.PROJECT1.controller;


import com.example.PROJECT1.DTO.CategoryDTO;
import com.example.PROJECT1.entities.Category;
import com.example.PROJECT1.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
    Category createdCategory=adminService.createCategory(categoryDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> allCategories =adminService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    } 
}
