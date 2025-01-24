package com.vladproduction.concurrency.exercises;

import java.util.concurrent.*;

/**
 * Exercise 10: (4) Modify Exercise 5 following the example of the ThreadMethod class,
 * so that runTask( ) takes an argument of the number of Fibonacci numbers to sum, and each
 * time you call runTask( ) it returns the Future produced by the call to submit( ).
 * */
public class Exercise10 {
    private ExecutorService executorService;
    private int[] tasks = {0,1,2,3,5,7,10,12};
    private Future<Long>[] result;

    public Exercise10() {
        executorService = Executors.newFixedThreadPool(tasks.length);
        result = new Future[tasks.length];
    }

    public Future<Long> runTask(int n){
        FibonacciSumTaskCallable task = new FibonacciSumTaskCallable(n);
        return executorService.submit(task);
    }

    public void executeAllTasks(){

        //submit tasks and store future:
        for (int i = 0; i < tasks.length; i++) {
            result[i] = runTask(tasks[i]);
        }

        //retrieve and print results
        for (int i = 0; i < result.length; i++) {
            try {
                Long value = result[i].get();
                System.out.println("Sum of first " + tasks[i] + " Fibonacci numbers: " + value);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        //shutdown executor
        executorService.shutdown();

        System.out.println("All Fibonacci sum tasks have completed execution.");

    }

    public static void main(String[] args) {
        Exercise10 exercise10 = new Exercise10();
        exercise10.executeAllTasks();
    }

}

class FibonacciSumTaskCallable implements Callable<Long> {
    private int n;

    public FibonacciSumTaskCallable(int n) {
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
