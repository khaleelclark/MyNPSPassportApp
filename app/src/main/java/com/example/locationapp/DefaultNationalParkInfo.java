package com.example.locationapp;

public class DefaultNationalParkInfo {
    private final String parkName;
    private final String parkState;
    private final String parkDescription;

    public DefaultNationalParkInfo(String parkName, String parkState, String parkDescription) {
        this.parkName = parkName;
        this.parkState = parkState;
        this.parkDescription = parkDescription;
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
}
