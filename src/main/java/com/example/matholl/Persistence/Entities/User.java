package com.example.matholl.Persistence.Entities;

import javax.persistence.*;

import java.util.List;

/*
    TODO: Er aldrei að búa til notanda fyrir innskráningu. Breyta þessu í að vera frekar notendastillingar sem notandi
    TODO: getur stillt á prófíl síðunni sinni. T.d. notendanafn. Gæti einnig haldið utan um hluti eins og comment
    TODO: sem notandi hefur skrifað, uppskriftir sem notandi hefur póstað, eitthvað með "Karma?", fær stig fyrir eitthvað
    TODO: getur sett inn heimilisfang og aðrar upplýsingar, en það er örugglega óþarfi.

    TODO: Ætti að þurfa id, username, email, comment[], recipe[], first_name, last_name, karma

    TODO: Notification/Messages system? Karma system?
*/

@Entity
@Table(name = "users")
public class User {

    private long ID;
    private String firstName;
    private String lastName;
    private String preferredUsername;
    private String email;
    //private List<Recipe> recipesUploadedByUser;
    private Comment[] commentsPostedByUser;

    // TODO: Need to figure out how I will be storing images. Cloudinary? Something else maybe better now.
    private String userPictureURL;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    /*
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Recipe> getRecipesUploadedByUser() {
        return recipesUploadedByUser;
    }
    */

    public void setCommentsPostedByUser(Comment[] commentsPostedByUser) {
        this.commentsPostedByUser = commentsPostedByUser;
    }

    public Comment[] getCommentsPostedByUser() {
        return commentsPostedByUser;
    }

    /*
    public void setRecipesUploadedByUser(List<Recipe> recipesUploadedByUser) {
        this.recipesUploadedByUser = recipesUploadedByUser;
    }
     */

    public String getUserPictureURL() {
        return userPictureURL;
    }

    public void setUserPictureURL(String userPictureURL) {
        this.userPictureURL = userPictureURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public User(String preferredUsername, String email, String firstName, String lastName) {
        this.preferredUsername = preferredUsername;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    // Add the toString() method

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", preferredUsername='" + preferredUsername + '\'' +
                ", email='" + email + '\'' +
                ", commentsPostedByUser=" + commentsPostedByUser +
                ", userPictureURL='" + userPictureURL + '\'' +
                '}';
    }

}
