package com.example.locationapp;

public class NationalParkInstanceInitializer {
    private int id;
    private String parkName;
    private boolean hasVisited = false;
    private boolean hasCompleted = false;

    public NationalParkInstanceInitializer(int id, String parkName) {
        this.id = id;
        this.parkName = parkName;
    }

    public int getId() {
        return id;
    }

    public String getParkName() {
        return parkName;
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
}
