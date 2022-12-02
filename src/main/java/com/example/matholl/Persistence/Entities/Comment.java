package com.example.matholl.Persistence.Entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/***************************************************************************
 *  Nafn     : Ólafur Pálsson
 *  T-póstur : olp10@hi.is
 *
 *  Lýsing   :
 *
 *
 ****************************************************************************/

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

    public LocalTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Comment(String commentBody, long recipeID, LocalDate dateCreated, LocalTime timeCreated) {
        this.commentBody = commentBody;
        this.recipeID = recipeID;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
    }

    public Comment() {

    }

    private LocalTime timeCreated;
}
