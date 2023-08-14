package com.example.matholl.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "available_categories")
public class AvailableCategory {

    private Long ID;
    private String name;
    private String icelandicName;

    public AvailableCategory() {

    }

    public AvailableCategory(String name, String icelandicName) {
        this.name = name;
        this.icelandicName = icelandicName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcelandicName() {
        return icelandicName;
    }

    public void setIcelandicName(String icelandicName) {
        this.icelandicName = icelandicName;
    }
}
