package com.vladproduction.concurrency.exercises;

/**
 * Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that
 * produces a sequence of n Fibonacci numbers, where n is provided to the constructor of the
 * task. Create a number of these tasks and drive them using threads.
 * */
public class Exercise2 {
    public static void main(String[] args) {

        // Number of Fibonacci numbers to generate for each task
//        int[] taskSizes = {3, 5, 4, 7};
        int[] taskSizes = {3};

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

class FibonacciTask implements Runnable{

    private final int n;

    public FibonacciTask(int n, String name) {
        this.n = n;
    }

    @Override
    public void run() {
        long[] fibonacci = generateFibonacci(this.n);
        System.out.println("Fibonacci sequence for n: " + this.n);
        for (int i = 0; i < fibonacci.length; i++) {
            System.out.println(Thread.currentThread().getName() + "[n: "  + n + "]" + " (step " + i + ") : " + fibonacci[i]);
        }
    }

    //generator method based on 'n'
    private long[] generateFibonacci(int n){
        long[] fibonacci = new long[n];
        if(n > 0){
            fibonacci[0] = n; //first fibonacci number
        }
        if(n > 1){
            fibonacci[1] = n; //second fibonacci number
        }
        //fibonacci formula:
        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}

