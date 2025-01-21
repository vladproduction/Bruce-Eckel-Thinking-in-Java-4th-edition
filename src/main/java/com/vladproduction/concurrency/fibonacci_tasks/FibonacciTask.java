package com.vladproduction.concurrency.fibonacci_tasks;

public class FibonacciTask implements Runnable{

    private int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        long[] fibonacci = generateFibonacci(n);
        System.out.print("Fibonacci sequence for n: " + n + ": ");
        for (long num : fibonacci) {
            System.out.print(num + " ");
        }
        System.out.println();
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
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }
}
