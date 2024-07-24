/***************************************************************************
 * Recipe.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Persistence.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The Recipe model entity
 */
@Entity
@Table(name = "recipes")
public class Recipe implements Comparable<Recipe> {

    /**
     * The ID of the recipe
     */
    private long ID;

    /**
     * The name of the recipe
     */
    private String name;

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
     * The user who uploaded the recipe
     */
    private User user;

    /**
     * The difficulty of cooking
     */
    private Difficulty difficulty;

    /**
     * The category of the recipe
     */
    private String category;

    /**
     * The instructions for the recipe
     */
    private String instructions;

    /**
     * The base image of the recipe
     */
    private String baseImage;

    /**
     * The constructor
     * @param instructions The instructions for the recipe
     * @param name The name of the recipe
     * @param timeToCookInMinutes The average time of cooking the recipe in minutes
     * @param forNumberOfPeople The number of people the recipe is suggested for
     * @param category The category of the recipe
     */
    public Recipe(Difficulty difficulty, String instructions, String name, int timeToCookInMinutes, int forNumberOfPeople, String category) {
        this.name = name;
        this.instructions = instructions;
        this.timeToCookInMinutes = timeToCookInMinutes;
        this.forNumberOfPeople = forNumberOfPeople;
        this.difficulty = difficulty;
        this.category = category;
    }

    /**
     * A required empty constructor
     */
    public Recipe() {
        this.dateAdded = LocalDateTime.now();
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

    @Lob
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDateTime getDateAdded() { return dateAdded; }

    public void setDateAdded(LocalDateTime dateAdded) { this.dateAdded = dateAdded; }

    public int getForNumberOfPeople() { return forNumberOfPeople; }

    public void setForNumberOfPeople(int forNumberOfPeople) { this.forNumberOfPeople = forNumberOfPeople; }

    public int getTimeToCookInMinutes() { return timeToCookInMinutes; }

    public void setTimeToCookInMinutes(int timeToCookInMinutes) { this.timeToCookInMinutes = timeToCookInMinutes; }

    @Column(name = "name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() { return ID; }

    public void setID(long ID) { this.ID = ID; }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    @Override
    public int compareTo(Recipe o) {
        // Compare by time
        return this.getDateAdded().compareTo(o.getDateAdded());
    }
    @Override
    public String toString() {
        return "Recipe{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", timeToCookInMinutes=" + timeToCookInMinutes + '\'' +
                ", forNumberOfPeople=" + forNumberOfPeople + '\'' +
                ", dateAdded=" + dateAdded + '\'' +
                ", user=" + user + '\'' +
                ", difficulty=" + difficulty + '\'' +
                ", category='" + category + '\'' +
                ", instructions='" + instructions + '\'' +
                ", baseImage='" + baseImage + '\'' +
                '}';
    }
}
