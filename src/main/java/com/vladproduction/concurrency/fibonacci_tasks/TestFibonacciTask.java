package com.vladproduction.concurrency.fibonacci_tasks;

public class TestFibonacciTask {
    public static void main(String[] args) {

        // Number of Fibonacci numbers to generate for each task
        int[] taskSizes = {5, 7, 10, 12};

        //create  some tasks
        Thread[] threads = new Thread[taskSizes.length];

        // Initialize and start threads for each task
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FibonacciTask(i));
            threads[i].start();
        }

        //join
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All Fibonacci tasks completed execution.");

    }
}
