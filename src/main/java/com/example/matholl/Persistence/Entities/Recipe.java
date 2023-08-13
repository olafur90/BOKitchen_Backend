/***************************************************************************
 * Recipe.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Recipe model entity
 */
@Entity
@Table(name = "recipes")
public class Recipe {

    /**
     * The ID of the recipe
     */
    private long ID;

    /**
     * The name of the recipe
     */
    private String name;

    /**
     * A short description for the recipe
     */
    private String shortDescription;

    /**
     * A longer description for the recipe
     */
    private String description;

    /**
     * The average time of cooking the recipe in minutes
     */
    private int timeToCookInMinutes;

    /**
     * The number of people the recipe is suggested for
     */
    private int forNumberOfPeople;

    /**
     * The date the recipe was added to server
     */
    private LocalDateTime dateAdded;

    /**
     * The thumbnail image for the recipe
     */
    private String baseImage;

    /**
     * The user who uploaded the recipe
     */
    private User user;

    private Difficulty difficulty;

    private List<RecipeCategoryLink> recipeCategoryLinks = new ArrayList<>();

    /**
     * The constructor
     * @param instructions The instructions for the recipe
     * @param name The name of the recipe
     * @param summary A short description for the recipe
     * @param timeToCookInMinutes The average time of cooking the recipe in minutes
     * @param forNumberOfPeople The number of people the recipe is suggested for
     */
    public Recipe(String instructions, String name, String summary, int timeToCookInMinutes, int forNumberOfPeople) {
        this.name = name;
        this.baseImage = "";
        this.instructions = instructions;
        this.shortDescription = summary;
        this.timeToCookInMinutes = timeToCookInMinutes;
        this.forNumberOfPeople = forNumberOfPeople;
        this.dateAdded = LocalDateTime.now();
        this.difficulty = Difficulty.EASY;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Lob
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    private String instructions;

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    /**
     * A required empty constructor
     */
    public Recipe() {
        this.difficulty = Difficulty.EASY;
        this.dateAdded = LocalDateTime.now();
    }

    public LocalDateTime getDateAdded() { return dateAdded; }

    public void setDateAdded(LocalDateTime dateAdded) { this.dateAdded = dateAdded; }

    public int getForNumberOfPeople() { return forNumberOfPeople; }

    public void setForNumberOfPeople(int forNumberOfPeople) { this.forNumberOfPeople = forNumberOfPeople; }

    //@OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    //public List<Ingredient> getIngredients() { return ingredients; }
    //public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public int getTimeToCookInMinutes() { return timeToCookInMinutes; }

    public void setTimeToCookInMinutes(int timeToCookInMinutes) { this.timeToCookInMinutes = timeToCookInMinutes; }

    @Lob
    public String getShortDescription() { return shortDescription; }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    @Column(name = "name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() { return ID; }

    public void setID(long ID) { this.ID = ID; }

    public void addCategory(Category category) {
        RecipeCategoryLink link = new RecipeCategoryLink(this, category);
        recipeCategoryLinks.add(link);
        category.getRecipeCategoryLinks().add(link);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @OneToMany(mappedBy = "recipe")
    public List<RecipeCategoryLink> getRecipeCategoryLinks() {
        return recipeCategoryLinks;
    }

    public void setRecipeCategoryLinks(List<RecipeCategoryLink> recipeCategoryLinks) {
        this.recipeCategoryLinks = recipeCategoryLinks;
    }

}
