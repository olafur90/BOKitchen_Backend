/***************************************************************************
 * Comment.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Persistence.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "comments")
public class Comment {

    private long ID;
    private String commentBody;
    private long recipeID;
    private LocalDate dateCreated;

    @Id
    @Column(name = "CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Comment(String commentBody, long recipeID) {
        this.commentBody = commentBody;
        this.recipeID = recipeID;
        this.dateCreated = LocalDate.now();
    }

    public Comment() {
        this.dateCreated = LocalDate.now();
    }

    private LocalTime timeCreated;
}
