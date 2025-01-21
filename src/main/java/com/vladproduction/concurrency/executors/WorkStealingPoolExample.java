package com.vladproduction.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Work Stealing Pool: Designed for work-stealing algorithms for efficient work distribution.
 *
 * - Executors.newWorkStealingPool() to create a work-stealing pool, which allows threads to "steal" tasks from each other to improve efficiency.
 * - The loop submits 10 tasks to the executor.
 *      Each task simulates doing work for a random duration between 1 to 3 seconds. This is done by generating a random number representing the workload.
 * - Each task prints out which thread it is executing on, simulates doing work using Thread.sleep(), and then prints when it has completed.
 * - Graceful Shutdown:
 *
 * After submitting all tasks, the shutdown() method is called to stop accepting new tasks.
 * The awaitTermination() method waits for up to 10 seconds for the existing tasks to complete.
 * If they are still running after the timeout, shutdownNow() is invoked to forcibly terminate the tasks.
 * An InterruptedException is handled to ensure that if the main thread gets interrupted while waiting, it shuts down the executor immediately.
 * */
public class WorkStealingPoolExample {
    public static void main(String[] args) {

        //create work-stealing Pool
        ExecutorService executor = Executors.newWorkStealingPool();

        // Submit several tasks with varying workloads
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                long workload = (long) (Math.random() * 3) + 1; // Random workload between 1 and 3 seconds
                System.out.println("Starting task " + taskNumber + " in " + Thread.currentThread().getName());

                //simulate work by sleeping
                try{
                    Thread.sleep(workload * 1000); // Sleep for workload seconds
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();// Restore the interrupted status
                    System.out.println("Task #" + taskNumber + " was interrupted.");
                }

                System.out.println("Completed task " + taskNumber + " in " + Thread.currentThread().getName());
            });
        }

        // Initiate graceful shutdown
        executor.shutdown();

        //awaiting to terminate while all tasks to finish:
        try{
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("Forcing shutdown timeout...");
                executor.shutdown();
            }
        }catch (InterruptedException e){
            executor.shutdown();
            Thread.currentThread().interrupt();
            System.out.println("Executor interrupted during shutdown.");
        }

        System.out.println("All tasks completed!");
    }
}