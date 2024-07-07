package com.example.locationapp;

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
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE)
        }
)

public class NationalParkInstance {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId", index = true)
    private int userId;

    @ColumnInfo(name = "parkId", index = true)
    private int parkId;

    @ColumnInfo(name = "has_visited")
    private boolean hasVisited = false;

    @ColumnInfo(name = "has_completed")
    private boolean hasCompleted = false;

    @ColumnInfo(name = "num_of_visits")
    private int numOfVisits = 0;

    @ColumnInfo(name = "date_of_last_visit")
    private String dateOfLastVisit = null;

    @ColumnInfo(name = "date_of_last_completion")
    private String dateOfLastCompletion = null;

    @ColumnInfo(name = "notes")
    private String parkNotes;

    public NationalParkInstance(int parkId, int userId) {
    this.parkId = parkId;
    this.userId = userId;
    }
    public String getParkNotes() {
        return parkNotes;
    }
    public void setParkNotes(String parkNotes) {
        this.parkNotes = parkNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public boolean isHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public boolean isHasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }

    public String getDateOfLastVisit() {
        return dateOfLastVisit;
    }

    public void setDateOfLastVisit(String dateOfLastVisit) {
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public String getDateOfLastCompletion() {
        return dateOfLastCompletion;
    }

    public void setDateOfLastCompletion(String dateOfLastCompletion) {
        this.dateOfLastCompletion = dateOfLastCompletion;
    }
}
