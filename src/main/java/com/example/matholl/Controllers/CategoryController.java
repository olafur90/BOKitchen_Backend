package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.AvailableCategory;
import com.example.matholl.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flokkar")
@CrossOrigin(origins = {"http://localhost:4200", "https://bokitchen.up.railway.app/"})
public class CategoryController {

    // FIXME: categoryService currently using AvailableCategory class
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public List<AvailableCategory> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{name}")
    public AvailableCategory getCategoryByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @PostMapping(value = "/add")
    public AvailableCategory createNewCategory(@RequestBody AvailableCategory category) {
        return categoryService.save(category);
    }
}
