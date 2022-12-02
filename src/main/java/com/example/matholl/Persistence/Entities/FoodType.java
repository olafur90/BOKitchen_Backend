package com.example.matholl.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foodtypes")
public class FoodType {

    private String imageUrl;
    private long ID;
    private String name;
    private String icelandicName;

    public String getIcelandicName() {
        return icelandicName;
    }

    public void setIcelandicName(String icelandicName) {
        this.icelandicName = icelandicName;
    }

    public FoodType() { }

    public FoodType(String name, String imageUrl, String icelandicName) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.icelandicName = icelandicName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    @Lob
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
