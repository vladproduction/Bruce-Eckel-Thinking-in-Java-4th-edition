package com.vladproduction.concurrency.exercises;

import java.util.Timer;
import java.util.TimerTask;

public class Exercise13 {
    public static void main(String[] args) {
        // Number of timers to create
        int numberOfTimers = 10;

        // Delay and period for each timer's task
        long delay = 1000; // 1 second delay before task execution
        long period = 2000; // 2 seconds period between repeated tasks

        for (int i = 0; i < numberOfTimers; i++) {
            // Create a new Timer object
            Timer timer = new Timer("Timer-" + (i + 1));

            // Schedule a TimerTask
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": Timer Task executed.");
                }
            }, delay, period);
        }

        // Keep the main thread alive for some time to observe the timers
        try {
            Thread.sleep(10000); // Main thread sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Normally, you would cancel the timers after the operation
        System.out.println("Main thread finished. Timers will now be cancelled.");

    }
}
