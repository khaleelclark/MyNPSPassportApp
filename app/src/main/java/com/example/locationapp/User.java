package com.example.locationapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "first_name")
    public String firstName;

    public void user (String userName){
        this.userName = userName;
        this.firstName = firstName;
    }
}
