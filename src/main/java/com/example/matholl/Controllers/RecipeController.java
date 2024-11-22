/***************************************************************************
 * RecipeController.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Controllers;
import com.example.matholl.Persistence.Entities.Comment;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Persistence.Entities.RecipeDTO;
import com.example.matholl.Services.CommentService;
import com.example.matholl.Services.IngredientService;
import com.example.matholl.Services.RecipeService;
import com.example.matholl.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for the recipe API
 */
@RestController
@RequestMapping("uppskriftir")
@CrossOrigin(origins = {"http://localhost:4200", "https://bokitchen.up.railway.app/"})
public class RecipeController {
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private CommentService commentService;
    private UserService userService;

    /**
     * The constructor
     * @param recipeService The recipe service
     * @param ingredientService The ingredient service
     */
    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService, CommentService commentService, UserService userService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.commentService = commentService;
        this.userService = userService;
    }

    /**
     * Adds a new recipe to the database.
     * @param recipe The recipe object to be added.
     * @return Status CODE 'CREATED' if recipe is successfully created, 'BAD_REQUEST' otherwise.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<Void> createNewRecipe(@Validated @RequestBody Recipe recipe) {
        if (userService.findByEmail(recipe.getUser().getEmail()) == null) {
            userService.save(recipe.getUser());
        } else {
            recipe.setUser(userService.findByEmail(recipe.getUser().getEmail()));
        }

        boolean isSaved = recipeService.save(recipe) != null;
        return isSaved ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    /**
     * Gets all recipes from the server.
     * @return A list of recipes
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();

        if (recipes == null || recipes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no recipes are found
        }

        return ResponseEntity.ok(recipes); // Returns 200 OK with the list of recipes
    }

    /**
     * Gets DTOs of all recipes
     * @return
     */
    @GetMapping(value = "/minimum")
    public List<RecipeDTO> getMinimum() {
        return recipeService.getDTOs();
    }

    /**
     * Searches for recipes by a search query
     * @param query The query to search for
     * @return A list of recipes that match the query
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String query) {
        List<Recipe> recipes = recipeService.searchRecipes(query);

        if (recipes == null || recipes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no recipes are found
        }

        return ResponseEntity.ok(recipes); // Returns 200 OK with the list of recipes
    }

    /**
     * Gets recipes by category
     * @param category The category of the recipes to get
     * @return A list of recipes that match the category
     */
    @GetMapping(value = "/flokkar/{category}")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@PathVariable("category") String category) {
        List<Recipe> recipes = recipeService.findByCategory(category);

        if (recipes == null || recipes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no recipes are found
        }

        return ResponseEntity.ok(recipes); // Returns 200 OK with the list of recipes
    }

    /**
     * Gets a recipe by id
     * @param id The id of the recipe to get
     * @return The recipe with the given id if it exists else null
     */
    @GetMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipeByID(@PathVariable("recipeId") String id) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        if (recipe == null) {
            return ResponseEntity.notFound().build(); // Returns 404
        }
        return ResponseEntity.ok(recipe); // Returns 200 with the recipe
    }

    /**
     * Gets all comments for a recipe
     * @param id The id of the recipe
     * @return A list of comments
     */
    @GetMapping(value = "/recipe/{recipeId}/comments")
    public List<Comment> getCommentsByRecipeID(@PathVariable("recipeId") String id) {
        return commentService.findByRecipeID(Long.parseLong(id));
    }

    /**
     * Adds a comment to a recipe
     * @param comment
     */
    @PostMapping(value = "/recipe/{recipeId}/comments")
    public void addComment(@RequestBody Comment comment) {
        commentService.save(comment);
    }

    @GetMapping(value = "/mostRecentRecipe")
    public ResponseEntity<Recipe> getMostRecent() {
        List<Recipe> recipes = recipeService.findAll();
        if (recipes == null || recipes.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return ResponseEntity.ok(recipes.get(0));
    }

    @GetMapping(value = "/recipeCount")
    public int getRecipeCount() {
        return recipeService.findAll().size();
    }

    /**
     * Gets a list of recent recipes from the server
     * @param pageIndex The requested index, used for paging in frontend client
     * @param requestedNumberOfRecipes the number of requested recipes
     * @return A list of recipes
     */
    @GetMapping(value = "/recentRecipes")
    public ResponseEntity<List<Recipe>> getRecentRecipes(@RequestParam int pageIndex, @RequestParam int requestedNumberOfRecipes) {
        List<Recipe> recipes = recipeService.findAll();
        int totalNumberOfRecipes = recipes.size();

        if (totalNumberOfRecipes == 0) {
            return ResponseEntity.noContent().build(); // 204
        }

        else if (totalNumberOfRecipes < requestedNumberOfRecipes) {
            return ResponseEntity.ok(recipes);
        }

        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));

        int startIndex = (pageIndex) * requestedNumberOfRecipes;
        int endIndex = startIndex + requestedNumberOfRecipes;

        if (endIndex > totalNumberOfRecipes) {
            endIndex = totalNumberOfRecipes;
        }

        return ResponseEntity.ok(recipes.subList(startIndex, endIndex));
    }

    /**
     * Gets the latest recipe from the server
     * @return A recipe object
     */
    @GetMapping(value = "/latestRecipe")
    public ResponseEntity<Recipe> getLatestRecipe(){
        List<Recipe> recipes = recipeService.findAll();

        if (recipes == null || recipes.size() == 0) {
            return ResponseEntity.noContent().build(); // 204
        }

        recipes.sort((recipe1, recipe2) -> recipe2.getDateAdded().compareTo(recipe1.getDateAdded()));
        return ResponseEntity.ok(recipes.get(0));
    }

    /**
     * Edits a recipe
     * @param recipe The recipe to edit
     * @param id The id of the recipe
     * @return 200 if recipe is successfully edited, 405 Method Not Allowed otherwise
     */
    @PostMapping(value = "/{foodtype}/{id}/editRecipe")
    public ResponseEntity<Void> editRecipe(@RequestBody Recipe recipe, @PathVariable("id") String id) {
        recipe.setID(Long.parseLong(id));
        boolean isEdited = recipeService.save(recipe) != null;
        return isEdited ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
