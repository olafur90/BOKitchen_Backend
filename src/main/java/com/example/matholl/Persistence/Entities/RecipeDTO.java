package com.example.matholl.Persistence.Entities;
import java.time.LocalDateTime;

public class RecipeDTO {
    private Long id;
    private String name;
    private LocalDateTime dateAdded; // Use LocalDateTime or other appropriate type

    // Constructors
    public RecipeDTO() {}

    public RecipeDTO(Long id, String name, LocalDateTime dateAdded) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDateTime dateAdded) { this.dateAdded = dateAdded; }
}