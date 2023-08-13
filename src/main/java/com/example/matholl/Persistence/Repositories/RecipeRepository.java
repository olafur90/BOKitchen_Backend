package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Category;
import com.example.matholl.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public void delete(Recipe recipe);
    public Recipe findByID(long id);
    public List<Recipe> findAll();
    public List<Recipe> findRecipesByRecipeCategoryLinks(String foodType);
    public Category findCategoryByName(String name);
    public List<Recipe> save(List<Recipe> recipes);

    @Query("SELECT r FROM Recipe r WHERE " +
            "r.name LIKE CONCAT('%', :query,'%')" +
            "OR r.description LIKE CONCAT('%', :query,'%')" +
            "OR r.shortDescription LIKE CONCAT('%', :query,'%')")
    public List<Recipe> searchRecipes(String query);
}
