package com.example.matholl.Services.Implementation;

import com.example.matholl.Persistence.Entities.AvailableCategory;
import com.example.matholl.Persistence.Repositories.CategoryRepository;
import com.example.matholl.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<AvailableCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public AvailableCategory save(AvailableCategory category) {
        return categoryRepository.save(category);
    }
}
