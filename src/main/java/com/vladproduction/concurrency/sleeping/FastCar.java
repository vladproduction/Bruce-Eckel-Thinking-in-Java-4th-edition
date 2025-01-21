package com.vladproduction.concurrency.sleeping;

import java.util.concurrent.TimeUnit;

/**
 * FastCar Class: specialized type of car that races faster
 *
 * Inherits from the Car class. This type of car reduces its distance more quickly and sleeps for less time,
 *      simulating a faster racing behavior.
 * Overrides the run() method to change its distance reduction logic and pacing.
 * */
public class FastCar extends Car{

    //constructor
    public FastCar(int distance){
        super(distance);
    }

    // Override the run method to simulate faster racing behavior
    @Override
    public void run() {
        while (distance > 0) {
            System.out.print(status());
            distance -= (int) (Math.random() * 5) + 2; // Faster distance reduction
            try {
                TimeUnit.MILLISECONDS.sleep(50); // Less sleep, simulating faster racing
            } catch (InterruptedException e) {
                System.err.println("FastCar# " + id + " was interrupted.");
            }
        }
        System.out.print(status() + "\n");
    }


}
