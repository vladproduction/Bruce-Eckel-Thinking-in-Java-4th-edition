package com.vladproduction.concurrency.exercises;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10
 * seconds, then displays its sleep time and exits. Create and run a quantity (given on the
 * command line) of these tasks.
 * */
public class Exercise6 {
    public static void main(String[] args) {
        // Check for command line argument
        if (args.length != 1) {
            System.out.println("Usage: java Exercise6 <number_of_tasks>");
            return;
        }

        int numberOfTasks;
        try {
            numberOfTasks = Integer.parseInt(args[0]); // value of tasks is 2
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            executorService.execute(new SleepTask(i + 1));
        }
        
        // Shutdown the executor service
        executorService.shutdown();
    }
}

class SleepTask implements Runnable {
    private int taskId;

    public SleepTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        Random random = new Random();
        // Sleep for a random time between 1 and 10 seconds
        int sleepTime = random.nextInt(10) + 1; // Generates a number from 1 to 10
        try {
            System.out.println("Task " + taskId + " sleeping for " + sleepTime + " seconds.");
            Thread.sleep(sleepTime * 1000); // Convert to milliseconds
        } catch (InterruptedException e) {
            System.out.println("Task " + taskId + " was interrupted.");
            Thread.currentThread().interrupt(); // Restore the interrupt status
        }
        System.out.println("Task " + taskId + " has finished sleeping.");
    }
}
