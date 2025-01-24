package com.vladproduction.concurrency.exercises;

import java.util.concurrent.*;

/**
 * Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of
 * all the Fibonacci numbers. Create several tasks and display the results.
 * */
public class Exercise5 {
    public static void main(String[] args) {

        int[] tasks = {0, 1, 2, 3, 5, 7, 10, 12};
        //int[] tasks = {3}; //Sum of first 3 Fibonacci numbers: 2 --> F(0) + F(1) + F(2) = 0 + 1 + 1 = 2
        //int[] tasks = {12}; //Sum of first 12 Fibonacci numbers: 232 --> 0 + 1 + 1 + 2 + 3 + 5 + 8 + 13 + 21 + 34 + 55 + 89 = 232

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

class FibonacciSumTask implements Callable<Long> {

    private int n;

    public FibonacciSumTask(int n) {
        this.n = n;
    }

    @Override
    public Long call() {
        return sumFibonacci(n);
    }

    private Long sumFibonacci(int n) {

        if (n <= 0) return 0L;
        if (n == 1) return 0L; // Sum of 0 (first Fibonacci number) 0
        if (n == 2) return 1L; // Sum of 0 + 1 (first two Fibonacci numbers) 0 1

        long a = 0; // F(0)
        long b = 1; // F(1)
        long sum = a + b; // F(0) + F(1)

        for (int i = 2; i < n; i++) {
            long next = a + b; // Calculate next Fibonacci number
            sum += next; // Add next to sum
            a = b; // Move to next
            b = next;
        }
        return sum;
    }

}

/*F(0) = 0
F(1) = 1
F(2) = 1
F(3) = 2
F(4) = 3
F(5) = 5
F(6) = 8
F(7) = 13
F(8) = 21
F(9) = 34
F(10) = 55
F(11) = 89
F(12) = 144*/

