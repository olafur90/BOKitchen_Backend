package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.AvailableCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<AvailableCategory, Long> {
    public void delete (AvailableCategory category);
    public AvailableCategory save (AvailableCategory category);
    public List<AvailableCategory> findAll();
}
