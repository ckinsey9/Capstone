package com.example.Capstone.Models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
public class JobApp {

    //@Id
    //@GeneratedValue
    private int jobId;

    @NotNull
    @Size(min=5, max=30)
    private String name;

    @NotNull
    @Size(min=5, max=30)
    private String company;

    @NotNull
    @Size(min=5, max=50)
    private String description;

    //add validation for this?
    private double salary;

    @NotNull
    private String location;

    //add a priority field?


    public JobApp(String name, String company, String description, double salary, String location) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.salary = salary;
        this.location = location;
    }

    public JobApp() { }

    public int getJobId() {
        return jobId;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

//TODO: DECIDE ON MORE FIELDS FOR CLASS