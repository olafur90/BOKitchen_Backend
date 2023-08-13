package com.example.matholl.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "recipe_category_link")
public class RecipeCategoryLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public RecipeCategoryLink() {
    }

    public RecipeCategoryLink(Recipe recipe, Category category) {
        this.recipe = recipe;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Getters and setters
}