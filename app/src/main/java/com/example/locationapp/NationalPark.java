package com.example.locationapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "national_park")
public class NationalPark {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "park_name")
    private String parkName;

    @ColumnInfo(name = "park_state")
    private String parkState;

    @ColumnInfo(name = "park_description")
    private String parkDescription;

    public NationalPark(String parkName, String parkState, String parkDescription) {
        this.parkName = parkName;
        this.parkState = parkState;
        this.parkDescription = parkDescription;

    }
    public String getParkState() {
        return parkState;
    }

    public void setParkState(String parkState) {
        this.parkState = parkState;
    }

    public String getParkDescription() {
        return parkDescription;
    }

    public void setParkDescription(String parkDescription) {
        this.parkDescription = parkDescription;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
