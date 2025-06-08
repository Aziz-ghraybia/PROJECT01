package com.example.PROJECT1.service.admin;

import com.example.PROJECT1.DTO.CategoryDTO;
import com.example.PROJECT1.entities.Category;

import java.util.List;

public interface AdminService {
    Category createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();
}
