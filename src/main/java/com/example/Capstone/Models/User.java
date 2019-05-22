package com.example.Capstone.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;

    @Size(min=1, max=50)
    private String firstName;

    @Size(min=1, max=50)
    private String lastName;

    @NotNull
    @Size(min=8, max=30)
    private String username;

    @NotNull
    @Size(min=8, max=30)
    private String password;


    @Size(min=8, max=30)
    private String verify;

    @NotNull
    @Size(min=1, max=100, message = "Please enter a valid email")
    private String email;


    //TODO: FIGURE OUT WHY MYSQL HAS TWO "USER_ID" COLUMNS
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<App> apps = new ArrayList<>();

    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public int getUserId() {
        return userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    //added to have each user's list of apps
    public List<App> getApps() {
        return apps;
    }

    //Add Method
    public void addApp(App app)  {
        apps.add(app);
    }
}
