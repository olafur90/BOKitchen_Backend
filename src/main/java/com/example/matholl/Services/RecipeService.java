package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.Category;
import com.example.matholl.Persistence.Entities.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> findAll();
    public Recipe findRecipeByID(long id);
    public List<Recipe> findRecipesByCategories(String foodType);
    public List<Recipe> findRecipesWithTimeBetween(int minTime, int maxTime);
    public Category findFoodTypeByName(String name);
    public Recipe save(Recipe recipe);
    public List<Recipe> save(List<Recipe> recipes);
    public void delete(Recipe recipe);
}
