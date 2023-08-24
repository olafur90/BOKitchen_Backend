/***************************************************************************
 * RecipeController.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Controllers;
import com.example.matholl.Persistence.Entities.Comment;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Services.CommentService;
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
@RequestMapping("uppskriftir")
@CrossOrigin(origins = {"http://localhost:4200", "https://mathollfrontend-production.up.railway.app/"})
public class RecipeController {
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private CommentService commentService;

    /**
     * The constructor
     * @param recipeService The recipe service
     * @param ingredientService The ingredient service
     */
    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService, CommentService commentService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.commentService = commentService;
    }

    /**
     * Creates a new recipe on the server
     * @param recipe The recipe to create
     * @return CREATED if recipe is successfully created, BAD_REQUEST if not
     */
    @PostMapping(value = "/add")
    public HttpStatus creteNewRecipe(@RequestBody Recipe recipe) {
        if (
                recipe.getName() == null ||
                //recipe.getCategory() == null ||
                recipe.getInstructions() == null ||
                recipe.getForNumberOfPeople() == 0 ||
                recipe.getTimeToCookInMinutes() == 0 ||
                recipe.getDifficulty() == null
        ) {
            return HttpStatus.BAD_REQUEST;
        }

        if (recipeService.save(recipe) != null) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.BAD_REQUEST;
    }

    /**
     * Gets all recipes on the server
     * @return A list of recipes
     */
    @GetMapping(value = "/")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return recipes;
    }

    /**
     * Searches for recipes by a query
     * @param query The query to search for
     * @return A list of recipes that match the query
     */
    @GetMapping(value = "/search")
    public List<Recipe> searchRecipes(@RequestParam String query) {
        List<Recipe> recipes = recipeService.searchRecipes(query);
        if (recipes.isEmpty()) {
            return new ArrayList<>();
        }
        return recipes;
    }

    /**
     * Gets recipes by category
     * @param category The category of the recipes to get
     * @return A list of recipes that match the category
     */
    @GetMapping(value = "/flokkar/{category}")
    public List<Recipe> getRecipesByCategory(@PathVariable("category") String category) {
        List<Recipe> recipes = recipeService.findByCategory(category);
        return recipes;
    }

    /**
     * Gets a recipe by id
     * @param id The id of the recipe to get
     * @return The recipe with the given id if it exists else null
     */
    @GetMapping(value = "/recipe/{recipeId}")
    public Recipe getRecipeByID(@PathVariable("recipeId") String id) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        return recipe;
    }

    /**
     * Gets all comments for a recipe
     * @param id The id of the recipe
     * @return A list of comments
     */
    @GetMapping(value = "/recipe/{recipeId}/comments")
    public List<Comment> getCommentsByRecipeID(@PathVariable("recipeId") String id) {
        List<Comment> comments = commentService.findByRecipeID(Long.parseLong(id));
        return comments;
    }

    /**
     * Adds a comment to a recipe
     * @param comment
     */
    @PostMapping(value = "/recipe/{recipeId}/comments")
    public void addComment(@RequestBody Comment comment) {
        commentService.save(comment);
    }

    /**
     * Gets a list of recent recipes from the server
     * @return A list of recipes
     */
    @GetMapping(value = "/recentRecipes")
    public List<Recipe> getRecentRecipes(){
        List<Recipe> recipes = recipeService.findAll();

        int maxNumberOfRecents = 7;
        int totalNumberOfRecipes = recipes.size();

        if (totalNumberOfRecipes == 0) {
            return null;
        }

        else if (totalNumberOfRecipes < maxNumberOfRecents) {
            return recipes;
        }

        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return recipes.subList(0, maxNumberOfRecents);
    }

    /**
     * Gets the latest recipe from the server
     * @return A recipe object
     */
    @GetMapping(value = "/latestRecipe")
    public Recipe getLatestRecipe(){
        List<Recipe> recipes = recipeService.findAll();

        if (recipes == null || recipes.size() == 0) {
            return null;
        }

        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return recipes.get(0);
    }

    /**
     * Edits a recipe
     * @param recipe The recipe to edit
     * @param id The id of the recipe
     * @return 200 if recipe is successfully edited, 405 Method Not Allowed otherwise
     */
    @PostMapping(value = "/{foodtype}/{id}/editRecipe")
    public HttpStatus editRecipe(Recipe recipe, @PathVariable("id") String id) {
        recipe.setID(Long.parseLong(id));
        if (recipeService.save(recipe) != null) {
            return HttpStatus.OK;
        }

        return HttpStatus.METHOD_NOT_ALLOWED;
    }
}
