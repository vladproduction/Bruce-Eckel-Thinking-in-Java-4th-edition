package com.vladproduction.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 4: (1) Repeat Exercise 2 using the different types of executors shown in this
 * section.
 * */
public class Exercise4 {
    public static void main(String[] args) {

        //newCachedThreadPool()
        ExecutorService executorServiceCashed = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorServiceCashed.execute(new FibonacciTask(4, "fibCashed " + i));
        }
        executorServiceCashed.shutdown();

        //newFixedThreadPool(2)
        ExecutorService executorServiceFixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            executorServiceFixedThreadPool.execute(new FibonacciTask(4, "fibFixed " + i));
        }
        executorServiceFixedThreadPool.shutdown();

        //newSingleThreadExecutor()
        ExecutorService executorServiceSingleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            executorServiceSingleThreadPool.execute(new FibonacciTask(5, "fibSingle " + i));
        }
        executorServiceSingleThreadPool.shutdown();

    }
}
