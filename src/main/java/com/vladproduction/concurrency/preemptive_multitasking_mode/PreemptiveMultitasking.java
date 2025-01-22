package com.vladproduction.concurrency.preemptive_multitasking_mode;

/**
 * Implementing a complete preemptive multitasking model in Java requires a level of control over CPU scheduling and interrupts
 * that is not typically feasible due to Java's abstraction from the hardware.
 * However, we can simulate the concept of preemptive multitasking using threads and a simple time-slicing method.
 *
 * Here's a basic example that demonstrates the preemptive multitasking concept in a Java application
 * using a scheduler and time quanta:
 * */
public class PreemptiveMultitasking {
    public static void main(String[] args) {
        // Define tasks, with their total execution times and time slices.
        Task task1 = new Task("Task 1", 5000, 1000); // 5 seconds total, 1 second time slice
        Task task2 = new Task("Task 2", 3000, 800);  // 3 seconds total, 800 ms time slice
        Task task3 = new Task("Task 3", 4000, 2000); // 4 seconds total, 2 seconds time slice

        // Create threads for each task
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        // Start all tasks
        thread1.start();
        thread2.start();
        thread3.start();
        
        // Wait for all tasks to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks have completed execution.");
    }
}
