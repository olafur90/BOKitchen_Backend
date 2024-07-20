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
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

}
