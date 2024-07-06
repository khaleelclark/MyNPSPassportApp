package com.example.locationapp;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //make private
    public static ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        viewModel.getAllNationalParks().observe(this, nationalParks -> {
            for(NationalPark nationalPark : nationalParks) {
                System.out.println(nationalPark.parkName);
            }
        });
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
    public void changeToSignUpScreen(View view) {
        setContentView(R.layout.activity_login);
    }
}