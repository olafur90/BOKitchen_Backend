package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> findAll();
    public Recipe findRecipeByID(long id);
    public List<Recipe> findRecipesByCategory(String foodType);
    public List<Recipe> findRecipesWithTimeBetween(int minTime, int maxTime);
    public Recipe save(Recipe recipe);
    public List<Recipe> save(List<Recipe> recipes);
    public void delete(Recipe recipe);
    public List<Recipe> searchRecipes(String query);
}
