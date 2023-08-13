package com.example.matholl.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The category model class
 */
@Entity
@Table(name = "categories")
public class Category {
    private Long ID;

    /**
     * The image URI
     */
    private String ImageUri;

    /**
     * The name of the category to be used as a slug
     */
    private String Name;

    /**
     * The full name of the category to be displayed on the front end
     */
    private String IcelandicName;

    private List<RecipeCategoryLink> recipeCategoryLinks = new ArrayList<>();

    /**
     * A required empty constructor
     */
    public Category() { }

    /**
     * The constructor
     * @param name The name of the category
     * @param imageUrl The image URI
     * @param icelandicName The display name of the category
     */
    public Category(String name, String imageUrl, String icelandicName) {
        this.Name = name;
        this.ImageUri = imageUrl;
        this.IcelandicName = icelandicName;
    }

    public String getIcelandicName() {
        return IcelandicName;
    }

    public void setIcelandicName(String icelandicName) {
        this.IcelandicName = icelandicName;
    }

    @Lob
    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        this.ImageUri = imageUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @OneToMany(mappedBy = "category")
    public List<RecipeCategoryLink> getRecipeCategoryLinks() {
        return recipeCategoryLinks;
    }

    public void setRecipeCategoryLinks(List<RecipeCategoryLink> recipeCategoryLinks) {
        this.recipeCategoryLinks = recipeCategoryLinks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
