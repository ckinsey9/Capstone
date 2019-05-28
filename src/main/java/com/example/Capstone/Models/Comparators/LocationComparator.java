package com.example.Capstone.Models.Comparators;

import com.example.Capstone.Models.App;

import java.util.Comparator;

public class LocationComparator implements Comparator<App> {
    @Override
    public int compare(App o1, App o2) {
        return o1.getLocation().compareTo(o2.getLocation());
    }
}
