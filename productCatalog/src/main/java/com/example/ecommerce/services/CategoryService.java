package com.example.ecommerce.services;

import com.example.ecommerce.models.Category;
import com.example.ecommerce.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }
}
