package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.FoodType;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public Recipe save(Recipe recipe);
    public void delete(Recipe recipe);
    public Recipe findByID(long id);
    public List<Recipe> findAll();
    public List<Recipe> findRecipesByFoodType(String foodType);
    public FoodType findFoodTypeByName(String name);
    public List<Recipe> save(List<Recipe> recipes);

    
}
