package com.vladproduction.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Single Thread Executor: Ensures that tasks are executed sequentially in a single thread.
 * */
public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        for (int i = 0; i < 5; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Executing task " + taskNumber + " in " + Thread.currentThread().getName());
            });
        }
        
        executor.shutdown();
    }
}