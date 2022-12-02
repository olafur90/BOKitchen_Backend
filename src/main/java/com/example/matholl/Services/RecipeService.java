package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.FoodType;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> findAll();
    public Recipe findRecipeByID(long id);
    public List<Recipe> findRecipesByFoodType(String foodType);
    public List<Recipe> findRecipesWithTimeBetween(int minTime, int maxTime);
    public FoodType findFoodTypeByName(String name);
    public Recipe save(Recipe recipe);
    public List<Recipe> save(List<Recipe> recipes);
    public void delete(Recipe recipe);
}
