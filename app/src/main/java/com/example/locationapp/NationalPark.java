package com.example.locationapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "national_park")
public class NationalPark {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "park_name")
    public String parkName;

    @ColumnInfo(name = "park_state")
    public String parkState;

    @ColumnInfo(name = "park_description")
    public String parkDescription;
}
