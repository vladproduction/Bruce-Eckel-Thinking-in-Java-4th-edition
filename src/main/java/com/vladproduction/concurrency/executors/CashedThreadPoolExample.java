package com.vladproduction.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cached Thread Pool: Dynamically creates new threads as needed, and will reuse previously constructed threads when they are available.
 * */
public class CashedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.execute(()->{
                System.out.println("Executing task: " + taskId + " " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
