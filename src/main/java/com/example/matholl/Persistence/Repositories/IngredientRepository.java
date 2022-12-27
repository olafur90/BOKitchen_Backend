package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Ingredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public List<Ingredient> findIngredientsByRecipeID(long id);
}
