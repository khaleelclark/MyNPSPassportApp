package com.example.locationapp;

import java.util.ArrayList;

public class parkUtility {

    ArrayList<NationalParkInstance> parkList = new ArrayList<>();

    public void initializeParks(){
        parkList.add(new NationalParkInstance("Arches", "Utah", "Delicate", false, false));
        parkList.add(new NationalParkInstance("Bryce Canyon", "Utah", "Hoodoo", false, false));
        parkList.add(new NationalParkInstance("Canyonlands", "Utah", "Canyons", false, false));
        parkList.add(new NationalParkInstance("Capitol Reef", "Utah", "Pie", false, false));
        parkList.add(new NationalParkInstance("Zion", "Utah", "Angel's Landing", false, false));
    }
}