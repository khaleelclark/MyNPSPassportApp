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
    public User currentUser;
    private AppDatabase db;
    private CustomNationalParkInstanceAdapter customNationalParkInstanceAdapter;

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
            for(NationalParkInstance npi: nationalParkInstances2) {
                System.out.println(npi.getParkId()+", "+npi.getUserId()+", "+npi.isHasCompleted()+", "+npi.isHasVisited());
            }
        }).start();
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
                    System.out.println(recyclerView);
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