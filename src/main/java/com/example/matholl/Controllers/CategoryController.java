package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.Category;
import com.example.matholl.Services.CategoryService;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flokkar")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
}
