package com.example.locationapp;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.locationapp.Dao_Interfaces.UserDao;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText firstNameEditText;
    private Button registerButton;
    public User currentUser;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = AppDatabase.getDatabase(getApplicationContext());

        usernameEditText = findViewById(R.id.usernameEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            System.out.println("GOTHERE");
            registerUser(username, firstName);
        });


    }
    public void displayParksList(View view) {
        setContentView(R.layout.activity_list);
    }
    public void backToLoginScreen(View view) {
        setContentView(R.layout.activity_sign_up);
        usernameEditText = findViewById(R.id.usernameEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            registerUser(username, firstName);
        });
    }
   // public void collectUserParkInfo(View view) {
   //     setContentView(R.layout.activity_set_park_data);
  //  }
    public void changeToSignUpScreen(View view) {
        setContentView(R.layout.activity_login);
    }

    private void registerUser (String username, String firstName) {
        new Thread(() -> {
            System.out.println("STARTING THREAD");
            if(db.userDao().countByUsername(username) > 0 ) {
                System.out.println("USER EXIST");
            }
            else {
                db.userDao().insertAll(new User(username, firstName));
                 User user = db.userDao().findByName(username);

                List<NationalPark> nationalParks = db.nationalParkDao().getAllParks();
                for (NationalPark np : nationalParks) {
                    db.nationalParkInstanceDao().insertAll(new NationalParkInstance(np.getUid(), user.getUid()));
                }
                System.out.println(user.getUserName());

                List<NationalParkInstance> nationalParkInstances = db.nationalParkInstanceDao().findInstancesByUserId(user.getUid());

                runOnUiThread( () -> {
                    setContentView(R.layout.activity_list);
                });
            }
        }).start();
    }
}