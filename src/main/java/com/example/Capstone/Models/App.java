package com.example.Capstone.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class App {

    @Id
    @GeneratedValue
    private int appId;

    @NotNull
    @Size(min=5, max=30)
    private String name;

    @NotNull
    @Size(min=5, max=30)
    private String company;

    //@NotNull
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private String date;

    @NotNull
    @Size(min=5, max=50)
    private String description;

    @NotNull
    private String salary;

    @NotNull
    private String location;

    //added fields
    @Size(max=250)
    private String notes;

    @Size(max=250)
    private String website;

    @NotNull
    private String phase;

    @ManyToOne
    private User user;

    public App(String name, String company, String description, String salary, String location,
               String notes, String website, String phase, String date) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.notes = notes;
        this.website = website;
        this.phase = phase;
        this.date = date;
    }

    public App() { }

    public int getAppId() {
        return appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //getters and setters for User

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAll(String name, String company, String description, String salary, String location,
                       String notes, String website, String phase, String date) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.notes = notes;
        this.website = website;
        this.phase = phase;
        this.date = date;
    }
}

//TODO: Fix date