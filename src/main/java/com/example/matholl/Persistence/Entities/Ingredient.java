package com.example.matholl.Persistence.Entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    private long ID;
    private String ingredientName;
    private Recipe recipe;
    private int cost;
    private String amount;

    @Id
    @Column(name = "IngredientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public void setCost(int cost) { this.cost = cost; }
    public int getCost() { return cost; }

    public void setAmount(String amount) { this.amount = amount; }
    public String getAmount() { return amount; }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Ingredient(String ingredientName, Recipe recipe) {
        this.ingredientName = ingredientName;
        this.recipe = recipe;
    }

    public Ingredient() {

    }
}
