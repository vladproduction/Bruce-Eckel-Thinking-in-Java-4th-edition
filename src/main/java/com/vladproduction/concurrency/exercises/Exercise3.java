package com.vladproduction.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 3: (1) Repeat Exercise 1 using the different types of executors shown in this
 * section.
 * */
public class Exercise3 {
    public static void main(String[] args) {
        //newCachedThreadPool()
        ExecutorService executorServiceCashed = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorServiceCashed.execute(new TaskRunnable("taskCashed " + i));
        }
        executorServiceCashed.shutdown();

        //newFixedThreadPool(2)
        ExecutorService executorServiceFixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            executorServiceFixedThreadPool.execute(new TaskRunnable("taskFixed " + i));
        }
        executorServiceFixedThreadPool.shutdown();

        //newSingleThreadExecutor()
        ExecutorService executorServiceSingleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            executorServiceSingleThreadPool.execute(new TaskRunnable("taskSingle " + i));
        }
        executorServiceSingleThreadPool.shutdown();

    }
}
