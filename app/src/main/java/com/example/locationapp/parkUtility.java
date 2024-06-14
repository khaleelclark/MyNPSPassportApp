package com.example.locationapp;

import java.util.ArrayList;

public class parkUtility {

    ArrayList<nationalParkInstance> parkList = new ArrayList<>();

    public void initializeParks(){
        parkList.add(new nationalParkInstance("Arches", "Utah", "Delicate", false, false));
        parkList.add(new nationalParkInstance("Bryce Canyon", "Utah", "Hoodoo", false, false));
        parkList.add(new nationalParkInstance("Canyonlands", "Utah", "Canyons", false, false));
        parkList.add(new nationalParkInstance("Capitol Reef", "Utah", "Pie", false, false));
        parkList.add(new nationalParkInstance("Zion", "Utah", "Angel's Landing", false, false));
    }
}