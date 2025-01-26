package com.vladproduction.concurrency.volatile_example;

/**
 * Visibility: When one thread modifies a volatile variable, that change is immediately visible to other threads.
 * This means that if a thread writes to a volatile variable, any subsequent reads of that variable will see the updated value,
 * even if those reads occur in different threads.
 *
 * No Caching: volatile variables are not cached in CPU registers. They are always read from and written to the main memory.
 * This helps avoid stale data being read from local caches used by threads.
 *
 * No Atomicity: It is important to note that volatile only guarantees visibility; it does not ensure atomicity.
 * If you need to perform compound actions (like check-then-act), you still need to use synchronized blocks or locks.
 *
 * More complex scenarios involving multiple operations, synchronization may still be necessary.
 *
 * Eventually:
 * The volatile keyword also ensures that changes are visible to the application.
 * If a field is declared with the volatile keyword, it means that immediately after
 * writing to that field, all reads will "see" the change. This is true
 * even when using local caches - volatile fields are immediately written to main memory, and reads are performed from main memory.
 * */
public class VolatileExample {

    private static volatile boolean running = true; // Declaring a volatile variable

    public static void main(String[] args) throws InterruptedException {

        // Create a thread that will stop after some time
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started.");
            while (running) {
                // Simulating some work
                // You could do something useful here
                for (int i = 1; i <= 5; i++) {
                    System.out.print(i + " ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println("Worker thread stopped.");
        });

        worker.start();

        // Main thread will let the worker run for a short time
        Thread.sleep(5000); // Wait for 5 second

        // Change the state of running to false
        running = false; //main thread changes the value of running, the worker thread will see the updated value immediately

        // Wait for the worker thread to finish
        worker.join();

        System.out.println("Main thread finished.");
    }
}
