package com.example.locationapp;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserNationalParkInfo> parkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void changeScreen(View view) {
        setContentView(R.layout.activity_list);
    }

    public void initializeParks(){
        parkList.add(new UserNationalParkInfo("Arches", "Utah", "Delicate", false, false));
        parkList.add(new UserNationalParkInfo("Bryce Canyon", "Utah", "Hoodoo", false, false));
        parkList.add(new UserNationalParkInfo("Canyonlands", "Utah", "Canyons", false, false));
        parkList.add(new UserNationalParkInfo("Capitol Reef", "Utah", "Pie", false, false));
        parkList.add(new UserNationalParkInfo("Zion", "Utah", "Angel's Landing", false, false));
    }
}