package com.example.matholl.Services.Implementation;


import com.example.matholl.Persistence.Entities.FoodType;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Persistence.Repositories.RecipeRepository;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImplementation implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public FoodType findFoodTypeByName(String name) {
        return recipeRepository.findFoodTypeByName(name);
    }

    @Override
    public List<Recipe> findAll() { return recipeRepository.findAll(); }

    @Override
    public List<Recipe> findRecipesByFoodType(String foodType) {
        return recipeRepository.findRecipesByFoodType(foodType);
    }

    @Override
    public Recipe findRecipeByID(long id) {
        return recipeRepository.findByID(id);
    }

    @Override
    public List<Recipe> save(List<Recipe> recipes) {
        return recipeRepository.save(recipes);
    }

    @Override
    public List<Recipe> findRecipesWithTimeBetween(int minTime, int maxTime) {
        return null;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }
}
