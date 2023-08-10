/***************************************************************************
 * RecipeController.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Controllers;
import com.example.matholl.Persistence.Entities.Category;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Services.IngredientService;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the recipe API
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {
    RecipeService recipeService;
    IngredientService ingredientService;

    /**
     * The constructor
     * @param recipeService The recipe service
     * @param ingredientService The ingredient service
     */
    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    /**
     * Creates a new recipe on the server
     * @param recipe The recipe to create
     * @return 201 if recipe is successfully created, 405 Method Not Allowed otherwise
     */
    @PostMapping(value = "/newRecipe")
    public HttpStatus creteNewRecipe(Recipe recipe) {
        if (recipeService.save(recipe) != null) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.METHOD_NOT_ALLOWED;
    }

    @GetMapping(value = "/uppskriftir")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return recipes;
    }

    @GetMapping(value = "/uppskriftir/search")
    public List<Recipe> searchRecipes(@RequestParam String query) {
        List<Recipe> recipes = recipeService.searchRecipes(query);
        if (recipes.isEmpty()) {
            return new ArrayList<>();
        }
        return recipes;
    }

    @GetMapping(value = "/uppskriftir/{recipeId}")
    public Recipe getRecipeByID(@PathVariable("recipeId") String id) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        return recipe;
    }

    /**
     * Gets a list of recent recipes from the server
     * @return A list of recipes
     */
    @GetMapping(value = "/uppskriftir/recentRecipes")
    public List<Recipe> getRecentRecipes(){
        int numberOfRecents = 6;
        List<Recipe> recipes = recipeService.findAll();
        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return recipes.subList(0, numberOfRecents);
    }

    /**
     * Gets the latest recipe from the server
     * @return A recipe object
     */
    @GetMapping(value = "/uppskriftir/latestRecipe")
    public Recipe getLatestRecipe(){
        int numberOfRecents = 1;
        List<Recipe> recipes = recipeService.findAll();
        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return recipes.get(0);
    }

    /**
     * Gets a list of all recipes given a food category
     * @param foodCategory The food category to get recipes for
     * @return A list of recipes
     */
    /*
    @GetMapping(value = "uppskriftir/{foodtype}")
    public List<Recipe> goToFoodType(@PathVariable("foodtype") String foodCategory) {
        List<Recipe> recipes = recipeService.findRecipesByCategories(foodCategory);
        return recipes;
    }*/

    /**
     * Gets all ingredients for a recipe
     * @param id The id of the recipe
     * @return A list of ingredients
     */
    @GetMapping(value = "/uppskriftir/{foodtype}/{id}/ingredients")
    public List<Ingredient> getRecipeIngredients(@PathVariable("id") String id) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        List<Ingredient> ingredients = ingredientService.findIngredientsByRecipeId(recipe.getID());
        return ingredients;
    }

    /**
     * Edits a recipe
     * @param recipe The recipe to edit
     * @param id The id of the recipe
     * @return 200 if recipe is successfully edited, 405 Method Not Allowed otherwise
     */
    @PostMapping(value = "/uppskriftir/{foodtype}/{id}/editRecipe")
    public HttpStatus editRecipe(Recipe recipe, @PathVariable("id") String id) {
        recipe.setID(Long.parseLong(id));
        if (recipeService.save(recipe) != null) {
            return HttpStatus.OK;
        }

        return HttpStatus.METHOD_NOT_ALLOWED;
    }
    @PostMapping(value = "/addDummyData")
    public HttpStatus dummyData(){
        addDummyData();
        return HttpStatus.OK;
    }
    private void addDummyData() {
        Category category = new Category("Food", null, "Matur");
        List<Category> cat = new ArrayList<>();
        cat.add(category);

        for (int i = 0; i < 20; i++) {
            Recipe recipe = new Recipe();
            recipe.setName("Recipe" + i);
            recipe.setShortDescription("Short Description" + i);
            recipe.setDescription("Description" + i);
            recipe.setTimeToCookInMinutes(10);
            recipe.setForNumberOfPeople(i);
            recipe.setDateAdded(LocalDateTime.now());
            recipe.setCategories(cat);

            Recipe returnRecipe = recipeService.save(recipe);
        }
    }

}
