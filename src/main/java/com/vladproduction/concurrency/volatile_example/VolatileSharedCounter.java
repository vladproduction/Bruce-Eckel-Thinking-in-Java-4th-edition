package com.vladproduction.concurrency.volatile_example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * While using volatile ensures visibility of the variable across threads, it doesn't provide atomicity for operations like incrementing.
 * Therefore, if run this program multiple times, we might not always get the expected output of 2000 due to race conditions.
 *
 * If atomicity is needed, consider using constructs like AtomicInteger. Here's an additional modification for atomicity
 *
 * Note:
 * If multiple tasks access a field at once, the field should be declared with the volatile keyword;
 * otherwise, the field should be accessed only through the synchronization mechanism.
 * Synchronization also forces data to be written to main memory, and if the field is fully protected by synchronized methods or blocks,
 * declaring it volatile is not necessary.
 * */
public class VolatileSharedCounter {
    public static void main(String[] args) {

        //create object to work with volatile:
        SharedCounter sharedCounter = new SharedCounter();

        // Creating two threads that will manipulate the shared counter
        // Increment the counter from thread 1
        Thread thread1 = new Thread(sharedCounter::incrementCounter);

        // Increment the counter from thread 2
        Thread thread2 = new Thread(sharedCounter::incrementCounter);

        // Starting both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After starting both threads with start(), join() is used to wait for both threads to complete
        // their execution before proceeding to print the final counter value

        // Print the final value of the shared counter
        System.out.println("Final Counter Value: " + sharedCounter.getCounter()); //expected: 2000; but it could lead race condition

        //example with AtomicDemo class using shared AtomicInteger variable:
        AtomicDemo atomicDemo = new AtomicDemo();
        Thread thread3 = new Thread(atomicDemo::incrementCounter);
        Thread thread4 = new Thread(atomicDemo::incrementCounter);
        Thread thread5 = new Thread(atomicDemo::incrementCounter);

        thread3.start();
        thread4.start();
        thread5.start();

        try{
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // Print the final value of the shared counter
        System.out.println("Final Counter AtomicInteger Value: " + atomicDemo.getCounter()); // expected: 3000



    }
}

class SharedCounter {

    // Using volatile keyword to ensure visibility of changes to this variable across threads;
    // Meaning any thread reading from this variable will always see the most recent write by any other thread;
    // This helps prevent caching issues which can occur when multiple threads interact with a variable;
    private volatile int counter = 0;

    //method to increment shared counter:
    //method increments the sharedCounter variable 1000 times.
    // Each increment operation is not atomic by itself; thus, this still introduces a potential issue when multiple threads
    // are incrementing this variable simultaneously.
    public void incrementCounter() {
        for (int i = 0; i < 1000; i++) {
            counter++;                      //incrementing value
        }
    }

    // method to get the current value of the shared counter;
    // since it is volatile, changes made by any thread are visible immediately to any other thread;
    public int getCounter(){
        return counter;
    }
}

/**
 * Using AtomicInteger ensures that the increments to the counter are atomic,
 * and each thread can safely update the shared value without race conditions.
 * */
class AtomicDemo {
    // Using AtomicInteger for thread-safe increments
    private AtomicInteger sharedCounter = new AtomicInteger(0);

    public void incrementCounter() {
        for (int i = 0; i < 1000; i++) {
            sharedCounter.incrementAndGet(); // Atomically increment the counter
        }
    }

    public int getCounter() {
        return sharedCounter.get(); // Get the current value atomically
    }
}

