package com.musalasoft.droneproject.exceptions;

public class DroneMaxWeightExceeded extends Exception {

    public DroneMaxWeightExceeded() {
        super("Drone Max Loading weight exceeded");
    }

    public DroneMaxWeightExceeded(String message) {
        super(message);
    }
}
