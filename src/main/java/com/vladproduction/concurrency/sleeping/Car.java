package com.vladproduction.concurrency.sleeping;

import java.util.concurrent.TimeUnit;

/**
 * Car Class: class representing a car in a race
 *
 * Represents a generic car in the race, implementing Runnable.
 * It maintains a distance to the finish line and has a static count to assign unique IDs.
 * The run() method simulates the racing process by decreasing the distance at a random rate,
 *  sleeping to represent time taken, and printing the car's status.
 * */
public class Car implements Runnable{

    protected int distance; // Distance to finish line
    private static int carCount = 0; // Static count of cars for IDs
    protected final int id; // Unique ID for the car

    //constructor
    public Car(int distance) {
        this.distance = distance;
        this.id = carCount++;
    }

    //method to get status of the car:
    public String status(){
        return "Car #" + id + " is at distance: " + (distance > 0 ? distance : "FINISHED!") + "; ";
    }

    // Run method simulating the race
    @Override
    public void run() {
        while (distance > 0) {
            System.out.print(status());
            distance -= (int) (Math.random() * 3) + 1; // Randomly decrease distance
            try {
                TimeUnit.MILLISECONDS.sleep(100); // Simulate racing time
            } catch (InterruptedException e) {
                System.err.println("Car# " + id + " was interrupted.");
            }
        }
        System.out.print(status() + "\n");
    }
}
