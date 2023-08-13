package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public void delete (Category category);
    public List<Category> findAll();
}
