package com.vladproduction.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolWithCallableExample {

    public static void main(String[] args) {
        // Create a fixed thread pool with a specified number of threads
        int numberOfThreads = 3; // Adjust to control concurrency
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        // Create an array to hold the results
        int numberOfTasks = 10;
        Future<Integer>[] futures = new Future[numberOfTasks];

        // Submit tasks
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskNumber = i;
            futures[i] = executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int result = performComplexCalculation(taskNumber);
                    System.out.println("Task " + taskNumber + " completed with result: " + result + " in " + Thread.currentThread().getName());
                    return result;
                }
            });
        }

        executor.shutdown(); // Initiate shutdown

        // Process results
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            try {
                // Get the result of each task
                Integer result = futures[i].get();
                results.add(result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error processing task " + i + ": " + e.getMessage());
            }
        }

        // Print all results
        System.out.println("Final Results: " + results);
    }

    // Simulates a complex calculation
    private static int performComplexCalculation(int taskNumber) {
        int workload = (int) (Math.random() * 3) + 1; // Random workload for simulation
        try {
            Thread.sleep(workload * 1000); // Simulate work by sleeping
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        return taskNumber * 10; // Example of a simple calculation
    }
}
