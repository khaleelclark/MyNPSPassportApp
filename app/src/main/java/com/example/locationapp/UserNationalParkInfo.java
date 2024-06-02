package com.example.locationapp;

import java.time.LocalDate;

public class UserNationalParkInfo {
    private final String parkName;
    private final boolean hasVisited = false;
    private final boolean hasCompleted = false;
    private final int numOfVisits = 0;
    private final LocalDate dateOfLastVisit = null;
    private final LocalDate dateOfLastCompletion = null;
    private String parkNotes;




    public UserNationalParkInfo(String parkName) {
        this.parkName = parkName;
    }

    public String getParkName() {
        return parkName;
    }

    public String getParkNotes() {
        return parkNotes;
    }

    public void setParkNotes(String parkNotes) {
        this.parkNotes = parkNotes;
    }
}
