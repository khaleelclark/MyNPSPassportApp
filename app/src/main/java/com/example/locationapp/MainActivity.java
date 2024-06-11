package com.example.locationapp;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parkUtility parkUtil = new parkUtility();
        parkUtil.initializeParks();
    }
    public void changeScreen(View view) {
        setContentView(R.layout.activity_list);
    }
    public void backToMainScreen(View view) {
        setContentView(R.layout.activity_main);
    }
    public void changeToSignUpScreen(View view) {
        setContentView(R.layout.activity_sign_up);
    }
}