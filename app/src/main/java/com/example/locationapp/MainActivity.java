package com.example.locationapp;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //db = MyApplication.getDatabase();

        parkUtility parkUtil = new parkUtility();
        parkUtil.initializeParks();
    }
    public void displayParksList(View view) {
        setContentView(R.layout.activity_list);
    }
    public void backToLoginScreen(View view) {
        setContentView(R.layout.activity_login);
    }
    public void collectUserParkInfo(View view) {
        setContentView(R.layout.activity_sign_up);
    }
}