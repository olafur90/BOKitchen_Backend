package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public List<Ingredient> findIngredientsByRecipeID(long id);
    public Ingredient save(Ingredient ingredient);
}
