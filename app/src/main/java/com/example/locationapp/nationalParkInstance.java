package com.example.locationapp;

import java.time.LocalDate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "national_park_instance"
        ,
        foreignKeys = {
                @ForeignKey(entity = NationalPark.class,
                        parentColumns = "uid",
                        childColumns = "parkId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "uid",
                        childColumns = "user_name",
                        onDelete = ForeignKey.CASCADE)
        }
)

public class nationalParkInstance  {

    private final String parkName;
    private final String parkState;
    private final String parkDescription;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "userId", index = true)
    public int userId;

    @ColumnInfo(name = "parkId", index = true)
    public int parkId;

    @ColumnInfo(name = "has_visited")
    public boolean hasVisited = false;

    @ColumnInfo(name = "has_completed")
    public boolean hasCompleted = false;

    @ColumnInfo(name = "num_of_visits")
    public int numOfVisits = 0;

    @ColumnInfo(name = "date_of_last_visit")
    public LocalDate dateOfLastVisit = null;

    @ColumnInfo(name = "date_of_last_completion")
    public LocalDate dateOfLastCompletion = null;

    @ColumnInfo(name = "notes")
    public String notes;

    public nationalParkInstance(String parkName, String parkState, String parkDescription, boolean hasVisited, boolean hasCompleted) {
        this.parkName = parkName;
        this.parkState = parkState;
        this.parkDescription = parkDescription;
        this.hasCompleted = hasCompleted;
        this.hasVisited = hasVisited;
    }
    public String getParkNotes() {
        return notes;
    }
    public void setParkNotes(String parkNotes) {
        this.notes = notes;
    }

    public String getParkName() {
        return parkName;
    }

    public String getParkState() {
        return parkState;
    }

    public String getParkDescription() {
        return parkDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
