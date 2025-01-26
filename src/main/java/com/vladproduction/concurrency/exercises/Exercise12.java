package com.vladproduction.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fix the error in AtomicityTesting.java by using the synchronized keyword.
 * Can you demonstrate that the program now works correctly?
 *
 * Proposed Solution
 * To resolve these issues and prevent the program from hogging CPU resources while also ensuring that the increment operations are consistent with the reads,
 * we need to take the following steps:
 *
 * 1)Introduce a Sleep Delay: Add a small sleep in the run() method to reduce CPU usage and allow other threads to execute.
 * 2)Use a Conditional Check: Instead of an infinite loop, check for a specific condition and break out of the loop after a certain number of increments or time.
 * 3)Atomic Operations: Consider using AtomicInteger to manage increment operations if you want a more robust solution without manual synchronization.
 * */
public class Exercise12 {
    public static void main(String[] args) {

        // Create executor service
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Create task
        AtomicityTesting at = new AtomicityTesting();
        // Submit task
        executorService.submit(at);

        // Print result
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) { // Condition to check for odd value
                System.out.print(val);
                executorService.shutdown(); // Shutdown the executor service
                System.exit(0); // Terminate the program
            }
        }

    }
}

class AtomicityTesting implements Runnable{

    private int i = 0;

    // Synchronized method to read value of i
    public synchronized int getValue() {
        return i;
    }

    // Synchronized method for even increment by 2
    private synchronized void evenIncrement() {
        i++;
        i++; // Increment twice to ensure even
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
            try {
                // Sleep for a short time to prevent hogging CPU
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                break; // Exit the loop if interrupted
            }
        }
    }
}














