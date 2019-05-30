package com.example.Capstone.Models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Pattern(regexp = "^[A-Za-z]{1,30}$", message =
            "Name must only use letters and be less than 30 characters")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]{1,30}$", message =
            "Name must only use letters and be less than 30 characters")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]{8,30}$", message = "Username must only use letters " +
            "or numbers and be 8-30 characters in length")
    private String username;

    @NotNull
    @Pattern(regexp = "^[\\S]{8,30}$", message = "Password can use any characters except spaces and " +
            "be 8-30 characters in length")
    private String password;


    @Pattern(regexp = "^[\\S]{8,30}$", message = "Password can use any characters except spaces and " +
            "be 8-30 characters in length")
    private String verify;

    @Size(min=1, message = "Please enter a valid address")
    private String address;

    @Email(message = "Please enter a valid email")
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<App> apps = new ArrayList<>();



    public User(String firstName, String lastName, String username, String password, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public User() {}

    public int getUserId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
