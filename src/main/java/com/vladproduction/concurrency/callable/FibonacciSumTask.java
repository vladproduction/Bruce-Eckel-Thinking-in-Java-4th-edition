package com.vladproduction.concurrency.callable;

import java.util.concurrent.Callable;

public class FibonacciSumTask implements Callable<Long> {

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
        if (n == 1) return 0L; // Sum of first Fibonacci number is 0
        if (n == 2) return 1L; // Sum of first two Fibonacci numbers is 1

        long a = 0, b = 1, sum = 1;
        for (int i = 0; i < n; i++) {
            long next = a + b;
            sum += next;
            a = b;
            b = next;
        }
        return sum;
    }

}
