package com.example.locationapp;

import java.time.LocalDate;

public class nationalParkInstance  {
    private boolean hasVisited = false;
    private boolean hasCompleted = false;
    private final int numOfVisits = 0;
    private final LocalDate dateOfLastVisit = null;
    private final LocalDate dateOfLastCompletion = null;
    private String notes;
    private final String parkName;
    private final String parkState;
    private final String parkDescription;

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
}
