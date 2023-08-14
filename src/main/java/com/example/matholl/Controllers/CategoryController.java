package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.AvailableCategory;
import com.example.matholl.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flokkar")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    // FIXME: categoryService currently using AvailableCategory class
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public List<AvailableCategory> getAllCategories() {
        return categoryService.findAll();
    }

    // TODO: Add category with endpoint
    @PostMapping(value = "/add")
    public AvailableCategory createNewCategory(@RequestBody AvailableCategory category) {
        return categoryService.save(category);
    }
}
