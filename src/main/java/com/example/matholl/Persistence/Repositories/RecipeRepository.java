package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Category;
import com.example.matholl.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public void delete(Recipe recipe);
    public Recipe findByID(long id);
    public List<Recipe> findAll();
    public List<Recipe> findRecipesByCategories(String foodType);
    public Category findCategoryByName(String name);
    public List<Recipe> save(List<Recipe> recipes);
}
