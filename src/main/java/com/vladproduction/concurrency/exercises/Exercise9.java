package com.vladproduction.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Exercise 9: (3) Modify SimplePriorities so that a custom ThreadFactory sets
 * the priorities of the threads.
 */
public class Exercise9 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new CustomThreadFactory());
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
            exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        }
        exec.shutdown();
    }

}

class SimplePriorities implements Runnable {

    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    // prints the thread name, priority, and group the thread belongs to;
    // also print output value of countDown
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // high loaded interrupted task
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}

// Custom ThreadFactory implementation
class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        // You can set the priority here by modifying this part if needed
        // For now, threads will inherit their priority from the Runnable upon execution.
        return thread;
    }
}
