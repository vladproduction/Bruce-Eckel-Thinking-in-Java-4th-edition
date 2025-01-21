package com.vladproduction.concurrency.fibonacci_tasks;

public class FibonacciTask implements Runnable{

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
