/*
 * Copyright (c) BÓ Kitchen -  2023
 *
 */

package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * This interface represents the repository for Recipe entities, providing methods for accessing and managing recipe data.
 *
 * @author BÓ Kitchen
 * @version 1.0
 * @since 2023
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /**
     * Deletes the given recipe from the repository.
     *
     * @param recipe The recipe to be deleted.
     */
    void delete(Recipe recipe);

    /**
     * Finds a recipe by its ID.
     *
     * @param id The ID of the recipe to be found.
     * @return The recipe with the given ID, or null if no such recipe exists.
     */
    Recipe findByID(long id);

    /**
     * Finds all recipes in the repository.
     *
     * @return A list of all recipes.
     */
    List<Recipe> findAll();

    /**
     * Finds all recipes in the repository that match the given category.
     *
     * @param category The category to search for.
     * @return A list of recipes that match the specified category.
     */
    List<Recipe> findByCategory(String category);

    /**
     * Saves a list of recipes to the repository.
     *
     * @param recipes The list of recipes to save.
     * @return A list of saved recipes.
     */
    List<Recipe> save(List<Recipe> recipes);

    /**
     * Saves a recipe to the repository.
     *
     * @param recipe The recipe to save.
     * @return The saved recipe.
     */
    Recipe save(Recipe recipe);

    /**
     * Searches for recipes containing the given query in their name or instructions.
     *
     * @param query The search query.
     * @return A list of recipes that match the search query.
     */
    @Query("SELECT r FROM Recipe r WHERE " +
            "r.name LIKE CONCAT('%', :query,'%')" +
            "OR r.instructions LIKE CONCAT('%', :query,'%')")
    List<Recipe> searchRecipes(String query);
}
