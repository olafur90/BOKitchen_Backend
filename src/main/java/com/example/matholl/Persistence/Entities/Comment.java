/***************************************************************************
 * Comment.java -- This file is part of matholl
 * Copyright (c) Ólafur Pálsson 2023. All rights reserved.
 ****************************************************************************/

package com.example.matholl.Persistence.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "comments")
public class Comment {

    private long ID;
    private String commentBody;

    private String userName;
    private long recipeID;
    private LocalDateTime dateCreated;

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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Comment(String commentBody, long recipeID, String userName) {
        this.commentBody = commentBody;
        this.recipeID = recipeID;
        this.dateCreated = LocalDateTime.now();
        this.userName = userName;
    }

    public Comment() {
        this.dateCreated = LocalDateTime.now();
    }

    private LocalTime timeCreated;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
