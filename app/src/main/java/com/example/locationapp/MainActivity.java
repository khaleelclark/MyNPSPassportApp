package com.example.locationapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.locationapp.Dao_Interfaces.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText firstNameEditText;
    private Button registerButton;
    private Button submitButton;
    private Button createAccountButton;
    //private Button mainButton;
    public User currentUser;
    private AppDatabase db;
    private CustomNationalParkInstanceAdapter customNationalParkInstanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = AppDatabase.getDatabase(getApplicationContext());

        createAccountButton = findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener((view) -> {
            setContentView(R.layout.activity_sign_up);
            usernameEditText = findViewById(R.id.usernameEditText);
            firstNameEditText = findViewById(R.id.firstNameEditText);
            registerButton = findViewById(R.id.registerButton);


            registerButton.setOnClickListener(v -> {
                String username = usernameEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                registerUser(username, firstName);
            });

        });
    }

    public void displayParksList(View view) {
        setContentView(R.layout.activity_list);
    }

    public void submitInitialInstances() {
        new Thread(() -> {
            List<NationalParkInstance> nationalParkInstances = db.nationalParkInstanceDao().findInstancesByUserId(currentUser.getUid());
            for(NationalParkInstanceInitializer npii : customNationalParkInstanceAdapter.getLocalDataSet()) {
                for(NationalParkInstance npi : nationalParkInstances) {
                    if(npii.getId() == npi.getParkId()) {
                        npi.setHasCompleted(npii.isHasCompleted());
                        npi.setHasVisited(npii.isHasVisited());
                        db.nationalParkInstanceDao().updateInstance(npi);
                    }
                }
            }
            List<NationalParkInstance> nationalParkInstances2 = db.nationalParkInstanceDao().findInstancesByUserId(currentUser.getUid());
            List<NationalPark> nationalParks = db.nationalParkDao().getAllParks();

            NationalParkListAdapter nationalParkListAdapter = new NationalParkListAdapter(nationalParkInstances2, nationalParks);

            runOnUiThread( () -> {
                setContentView(R.layout.main_activity_list);
                RecyclerView recyclerView = findViewById(R.id.np_main_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(nationalParkListAdapter);
            }  );
        }).start();
    }
   // public void collectUserParkInfo(View view) {
   //     setContentView(R.layout.activity_set_park_data);
  //  }
    public void changeToSignUpScreen(View view) {
        setContentView(R.layout.activity_sign_up);
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
                 currentUser = user;

                List<NationalPark> nationalParks = db.nationalParkDao().getAllParks();
                List<NationalParkInstanceInitializer> nationalParkInstanceInitializers = new ArrayList<>();
                for (NationalPark np : nationalParks) {
                    db.nationalParkInstanceDao().insertAll(new NationalParkInstance(np.getUid(), user.getUid()));
                    nationalParkInstanceInitializers.add(new NationalParkInstanceInitializer(np.getUid(), np.getParkName()));
                }

                customNationalParkInstanceAdapter = new CustomNationalParkInstanceAdapter(nationalParkInstanceInitializers);

                runOnUiThread( () -> {
                    setContentView(R.layout.activity_list);
                    RecyclerView recyclerView = findViewById(R.id.np_initializer_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(customNationalParkInstanceAdapter);
                    submitButton = findViewById(R.id.submitButton);

                    submitButton.setOnClickListener(v -> {
                        submitInitialInstances();
                    });
                });
            }
        }).start();
    }
}