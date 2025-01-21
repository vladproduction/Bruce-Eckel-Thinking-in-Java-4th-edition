package com.vladproduction.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WorkStealingPoolWithCallableExample {
    public static void main(String[] args) {
        // Create a work-stealing pool
        ExecutorService executor = Executors.newWorkStealingPool();

        // Create an array to hold the results
        int numberOfTasks = 10;
        Future<Integer>[] futures = new Future[numberOfTasks];

        // Submit tasks
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskNumber = i;
            futures[i] = executor.submit(() -> {
                int result = performComplexCalculation(taskNumber);
                System.out.println("Task " + taskNumber + " completed with result: " + result + " in " + Thread.currentThread().getName());
                return result;
            });
        }

        executor.shutdown();

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
        long workload = (long) (Math.random() * 3) + 1; // Random workload for simulation
        try {
            Thread.sleep(workload * 1000); // Simulate work by sleeping
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        return taskNumber * 10; // Example of a simple calculation
    }
}

/*Result Storage:
An array of Future<Integer> is defined to hold the results of the submitted tasks. The size of the array is defined by the number of tasks.

Callable Submission:
A for-loop is used to submit Callable<Integer> tasks to the executor. Each Callable performs a simulated complex calculation
based on the task number and returns an integer result.

Performing Calculations:
Within the performComplexCalculation(int taskNumber) method, a random workload is simulated with Thread.sleep(), and it returns a
simple calculation (in this case, task number multiplied by 10).

Executor Shutdown:
The executor is shut down after all tasks have been submitted using executor.shutdown().

Processing Results:
After shutting down the executor, another loop iterates through the futures array to retrieve the results of each task using get().
This method blocks until the result is available or an exception is thrown.
If an exception occurs during the retrieval of any result, it is caught and logged.

Final Results Display:
Finally, all collected results are printed to the console.*/
