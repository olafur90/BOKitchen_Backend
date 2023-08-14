package com.example.matholl.Services.Implementation;

import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Persistence.Repositories.RecipeRepository;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The recipe service implementation
 */
@Service
public class RecipeServiceImplementation implements RecipeService {

    /**
     * The recipe repository
     */
    private RecipeRepository recipeRepository;

    /**
     * The recipe service implementation constructor
     * @param recipeRepository The recipe repository
     */
    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> searchRecipes(String query) {
        return recipeRepository.searchRecipes(query);
    }

    @Override
    public List<Recipe> findAll() { return recipeRepository.findAll(); }

    @Override
    public List<Recipe> findRecipesByCategory(String foodType) {
        return recipeRepository.findRecipesByCategory(foodType);
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
