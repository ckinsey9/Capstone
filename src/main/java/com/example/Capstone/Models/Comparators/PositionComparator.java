package com.example.Capstone.Models.Comparators;

import com.example.Capstone.Models.App;

import java.util.Comparator;

public class PositionComparator implements Comparator<App> {
    @Override
    public int compare(App o1, App o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
