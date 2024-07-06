package com.example.locationapp;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    //make private
    public static ViewModel viewModel;
    private EditText usernameEditText;
    private EditText firstNameEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.usernameEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            registerUser(username, firstName);

            System.out.println(username + ' ' + firstName);
        });


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
        setContentView(R.layout.activity_set_park_data);
    }
    public void changeToSignUpScreen(View view) {
        setContentView(R.layout.activity_login);
    }

    private void registerUser (String username, String firstName){
        //save inputs to database
    }
}