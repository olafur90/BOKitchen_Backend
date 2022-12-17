package com.example.matholl.Persistence.Entities;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    private long ID;
    private String name;
    private String shortDescription;
    private String description;
    private String timeToCook;
    private String forNumberOfPeople;
    private LocalDate dateAdded;
    private String foodType;
    private String imageLink;
    private User user;
    private List<String> foodTypes;
    // private List<Ingredient> ingredients;



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
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
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Recipe() {

    }

    public LocalDate getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }

    public String getForNumberOfPeople() { return forNumberOfPeople; }
    public void setForNumberOfPeople(String numberOfPeople) { this.forNumberOfPeople = numberOfPeople; }

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    //public List<Ingredient> getIngredients() { return ingredients; }
    //public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public void addIngredient(Ingredient ingredient) {
        // this.ingredients.add(ingredient);
    }

    public String getTimeToCook() { return timeToCook; }
    public void setTimeToCook(String timeToCook) { this.timeToCook = timeToCook; }

    @Lob
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    @Column(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Recipe(String instructions, String name, String shortDescription, String timeToCook, String forNumberOfPeople, String foodType) {
        this.name = name;
        this.imageLink = "";
        this.instructions = instructions;
        this.shortDescription = shortDescription;
        this.timeToCook = timeToCook;
        this.forNumberOfPeople = forNumberOfPeople;
        this.dateAdded = LocalDate.now();
        this.foodType = foodType;
        //this.ingredients = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() { return ID; }

    public void setID(long ID) { this.ID = ID; }
}
