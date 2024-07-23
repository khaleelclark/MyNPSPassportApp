package com.example.locationapp;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.locationapp.Dao_Interfaces.NationalParkInstanceDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText firstNameEditText;
    private Button registerButton;
    private Button submitButton;
    private Button createAccountButton;
    private Button loginButton;
    private Button saveButton;
    private Button backButton;
    private Button increaseButton;
    private Button decreaseButton;
    private TextView numParksVisited;
    private TextView parkNotes;
    public User currentUser;
    private AppDatabase db;
    private CustomNationalParkInstanceAdapter customNationalParkInstanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = AppDatabase.getDatabase(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("National Park Passport Buddy");
        actionBar.setSubtitle("Designed by Zindel");
        actionBar.setDisplayUseLogoEnabled(true);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((l) -> {
            usernameEditText = findViewById(R.id.usernameEditText);
            String loginUsername = usernameEditText.getText().toString();
            loginUser(loginUsername);
        });


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

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void submitInitialInstances() {
        new Thread(() -> {
            List<NationalParkInstance> nationalParkInstances = db.nationalParkInstanceDao().findInstancesByUserId(currentUser.getUid());
            for (NationalParkInstanceInitializer npii : customNationalParkInstanceAdapter.getLocalDataSet()) {
                for (NationalParkInstance npi : nationalParkInstances) {
                    if (npii.getId() == npi.getParkId()) {
                        npi.setHasCompleted(npii.isHasCompleted());
                        npi.setHasVisited(npii.isHasVisited());
                        db.nationalParkInstanceDao().updateInstance(npi);
                    }
                }
            }
            switchToMainScreen();
        }).start();
    }

    public void switchToMainScreen() {
                new Thread(() -> {

            List<NationalParkInstance> nationalParkInstances2 = db.nationalParkInstanceDao().findInstancesByUserId(currentUser.getUid());
            List<NationalPark> nationalParks = db.nationalParkDao().getAllParks();

            NationalParkListAdapter nationalParkListAdapter = new NationalParkListAdapter(nationalParkInstances2, nationalParks, this);

            runOnUiThread( () -> {
                setContentView(R.layout.main_activity_list);
                RecyclerView recyclerView = findViewById(R.id.np_main_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(nationalParkListAdapter);
            }  );
        }).start();
    }

    private void loginUser(String loginUsername) {
        new Thread(() -> {
            System.out.println("STARTING THREAD");
            boolean userExists = db.userDao().countByUsername(loginUsername) > 0;
            if (userExists) {
                currentUser = db.userDao().findByName(loginUsername);
                System.out.println("LOGGED IN");
               switchToMainScreen();
            }
        }).start();
    }

    private void returnToLoginScreen() {
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((v) -> {
            String username = ((EditText) findViewById(R.id.usernameEditText)).getText().toString();
            loginUser(username);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setNationalParkScreen(NationalPark nationalPark, NationalParkInstance nationalParkInstance) {
        setContentView(R.layout.national_park_screen);
        Button editButton = findViewById(R.id.editParkButton);
        Button backButton = findViewById(R.id.backButton);
        TextView textView = findViewById(R.id.test);
        textView.setText(nationalPark.getParkName());

        TextView parkNotes = findViewById(R.id.parkNotes);
        parkNotes.setText(nationalParkInstance.getParkNotes());

        TextView parkTextView = findViewById(R.id.parkDescription);
        parkTextView.setText(nationalPark.getParkDescription());

        TextView visitsView = findViewById(R.id.visitCounter);
        visitsView.setText("Number of Visits: " + nationalParkInstance.getNumOfVisits());

        editButton.setOnClickListener((v) ->
            setNationalParkScreenEdit(nationalPark, nationalParkInstance)
        );
        backButton.setOnClickListener((v) ->
                switchToMainScreen()
        );
    }


    public void setNationalParkScreenEdit(NationalPark nationalPark, NationalParkInstance nationalParkInstance) {
        setContentView(R.layout.edit_national_park_screen);
        TextView textView = findViewById(R.id.test);
        textView.setText(nationalPark.getParkName());
        final int[] visitCount = {nationalParkInstance.getNumOfVisits()};

        TextView parkTextView = findViewById(R.id.parkDescription);
        parkTextView.setText(nationalPark.getParkDescription());

        Button decreaseButton = findViewById(R.id.decreaseButton);
        Button increaseButton = findViewById(R.id.increaseButton);
        TextView numParksVisited = findViewById(R.id.visitCounter);
        numParksVisited.setText("Number of Visits: " + visitCount[0]);

        decreaseButton.setOnClickListener((v) -> {
            if (visitCount[0] > 0) {
                visitCount[0]--;
                nationalParkInstance.setNumOfVisits(visitCount[0]);
            }
            numParksVisited.setText("Number of Visits: " + visitCount[0]);
        });

        increaseButton.setOnClickListener((v) -> {
            visitCount[0]++;
            numParksVisited.setText("Number of Visits: " + visitCount[0]);
            nationalParkInstance.setNumOfVisits(visitCount[0]);
        });

//get and set notes
        parkNotes = findViewById(R.id.parkNotes);

        new Thread(() -> {
            parkNotes.setText(db.nationalParkInstanceDao().getNotes(currentUser.getUid(), nationalParkInstance.getParkId()));
        }).start();


        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((l) -> setNationalParkScreen(nationalPark, nationalParkInstance));

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener((l) -> {
            new Thread(() -> {
                NationalParkInstanceDao nationalParkInstanceDao = db.nationalParkInstanceDao();
                nationalParkInstance.setParkNotes(parkNotes.getText().toString());
                nationalParkInstanceDao.updateInstance(nationalParkInstance);
                runOnUiThread( () -> setNationalParkScreen(nationalPark, nationalParkInstance));
            }).start();

        });

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

    public void logoutUser(MenuItem item) {
                new Thread(() -> {
                    System.out.println("LOGGED OUT");
                    runOnUiThread(() -> {
                        setContentView(R.layout.activity_login);
                        currentUser = null;
                        returnToLoginScreen();
                        initializeLoginScreen();
                    });
                }).start();
    }
    private void initializeLoginScreen () {
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((l) -> {
            usernameEditText = findViewById(R.id.usernameEditText);
            String loginUsername = usernameEditText.getText().toString();
            loginUser(loginUsername);
        });
    }
}

