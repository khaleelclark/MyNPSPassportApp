package com.example.locationapp;


import android.app.Application;
import androidx.room.Room;

public class MyApplication extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "nps_passport_app_database").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
