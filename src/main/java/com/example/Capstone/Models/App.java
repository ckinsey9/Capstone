package com.example.Capstone.Models;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;


@Entity
public class App {

    @Id
    @GeneratedValue
    private int appId;

    @NotNull
    @Size(min=1, message = "Required application info")
    private String name;

    @NotNull
    @Size(min=1, message = "Required application info")
    private String company;

    @NotNull
    private String date;

    @NotNull
    @Size(min=1, max=50, message = "Required application info")
    private String description;

    @NotNull
    private String salary;

    @NotNull
    @Size(min=1, message = "Please enter an address or general location")
    private String location;

    @Size(max=250)
    private String notes;

    //@Size(max=250)
    @URL(protocol = "https", message= "Please enter a valid URL starting with: 'https://'")
    private String website;

    @NotNull
    private String phase;

    @ManyToOne
    private User user;

    @NotNull
    @Min(0)
    @Max(10)
    private int positionRating;

    @NotNull
    @Min(0)
    @Max(10)
    private int companyRating;

    @NotNull
    @Min(0)
    @Max(10)
    private int locationRating;

    @NotNull
    @Min(0)
    @Max(10)
    private int benefitsRating;

    @NotNull
    @Min(0)
    @Max(10)
    private int salaryRating;

    public App(String name, String company, String description, String salary, String location,
               String notes, String website, String phase, String date, int positionRating,
               int companyRating, int locationRating, int benefitsRating, int salaryRating) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.notes = notes;
        this.website = website;
        this.phase = phase;
        this.date = date;
        this.positionRating = positionRating;
        this.companyRating = companyRating;
        this.locationRating = locationRating;
        this.benefitsRating = benefitsRating;
        this.salaryRating = salaryRating;
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
                       String notes, String website, String phase, String date, int positionRating,
                       int benefitsRating, int salaryRating, int companyRating, int locationRating) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.notes = notes;
        this.website = website;
        this.phase = phase;
        this.date = date;
        this.salaryRating = salaryRating;
        this.benefitsRating = benefitsRating;
        this.locationRating = locationRating;
        this.positionRating = positionRating;
        this.companyRating = companyRating;
    }

    public int getPositionRating() {
        return positionRating;
    }

    public void setPositionRating(int positionRating) {
        this.positionRating = positionRating;
    }

    public int getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(int companyRating) {
        this.companyRating = companyRating;
    }

    public int getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(int locationRating) {
        this.locationRating = locationRating;
    }

    public int getBenefitsRating() {
        return benefitsRating;
    }

    public void setBenefitsRating(int benefitsRating) {
        this.benefitsRating = benefitsRating;
    }

    public int getSalaryRating() {
        return salaryRating;
    }

    public void setSalaryRating(int salaryRating) {
        this.salaryRating = salaryRating;
    }

    public double totalRatingPercent() {
        ArrayList<Integer> total = new ArrayList<>();
        total.add(this.benefitsRating);
        total.add(this.companyRating);
        total.add(this.positionRating);
        total.add(this.salaryRating);
        total.add(this.locationRating);
        double finalTotal = 0;
        double counter = 0;
        for (Integer rating : total) {
            if (rating != 0) {
                finalTotal += rating;
                counter += 1;
            }
        }
        try {
            double returnValue = finalTotal / counter * 10;
            return Math.round(returnValue*100.0) / 100.0;
        } catch (ArithmeticException exception) {
            return 0;
        }
    }
}

