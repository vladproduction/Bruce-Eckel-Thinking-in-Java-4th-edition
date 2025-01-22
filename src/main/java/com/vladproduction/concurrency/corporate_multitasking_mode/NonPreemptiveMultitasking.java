package com.vladproduction.concurrency.corporate_multitasking_mode;

/**
 * Working in corporate (non-preemptive) multitasking mode:
 * demonstrates a simple way to manage tasks in a non-preemptive way, as one task must complete before the next one begins.
 * */
public class NonPreemptiveMultitasking {
    public static void main(String[] args) {
        // Create tasks
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        
        // Create threads for each task
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        
        // Start the first task
        thread1.start();
        try {
            thread1.join(); // Wait for Task 1 to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Start the second task
        thread2.start();
        try {
            thread2.join(); // Wait for Task 2 to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks have completed execution.");
    }
}
