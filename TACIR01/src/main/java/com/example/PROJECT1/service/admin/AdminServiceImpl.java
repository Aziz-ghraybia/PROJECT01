package com.example.PROJECT1.service.admin;

import com.example.PROJECT1.DTO.CategoryDTO;
import com.example.PROJECT1.entities.Category;
import com.example.PROJECT1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category=new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }
    @Override
    public List<CategoryDTO>  getAllCategories(){
       return categoryRepository.findAll().stream().map(Category::getCategoryDTO).collect(Collectors.toList());
    }
}
