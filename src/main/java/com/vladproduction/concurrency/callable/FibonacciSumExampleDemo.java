package com.vladproduction.concurrency.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciSumExampleDemo {
    public static void main(String[] args) {

        int[] tasks = {0, 1, 2, 3, 5, 7, 10, 12};

        ExecutorService executorService = Executors.newFixedThreadPool(tasks.length);

        //array to hold the future objects:
        Future<Long>[] results = new Future[tasks.length];

        //submitting tasks and store to the futures:
        for (int i = 0; i < tasks.length; i++) {
            FibonacciSumTask task = new FibonacciSumTask(tasks[i]);
            results[i] = executorService.submit(task);
        }

        //retrieve and print results:
        for (int i = 0; i < results.length; i++) {
            try {
                Long res = results[i].get();
                System.out.println("Sum of first " + tasks[i] + " Fibonacci numbers: " + res);
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        //shutdown:
        executorService.shutdown();
        System.out.println("All Fibonacci sum tasks have completed execution.");
    }
}
