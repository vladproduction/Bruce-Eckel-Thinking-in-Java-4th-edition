package com.vladproduction.concurrency.fibonacci_tasks;

public class TestFibonacciTask {
    public static void main(String[] args) {

        // Number of Fibonacci numbers to generate for each task
        int[] taskSizes = {3, 5, 4, 7};

        //create  some tasks
        Thread[] threads = new Thread[taskSizes.length];

        // Initialize and start threads for each task
        for (int i = 0; i < threads.length; i++) {
            String name = "Thread-";
            threads[i] = new Thread(new FibonacciTask(taskSizes[i], name + " " + i));
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
