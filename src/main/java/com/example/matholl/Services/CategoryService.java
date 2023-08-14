package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.AvailableCategory;

import java.util.List;

public interface CategoryService {
    public List<AvailableCategory> findAll();
    public AvailableCategory save(AvailableCategory category);
}
