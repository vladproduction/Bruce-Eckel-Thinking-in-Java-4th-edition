package com.vladproduction.concurrency.thread_runnable_simple_demo;

public class SimpleRunnable implements Runnable{

    private int decrement = 5;
    private Thread t = new Thread(this);

    public SimpleRunnable() {
        t.start();
    }

    public String toString() {
        return Thread.currentThread().getName() + "(" + decrement + ")";
    }

    @Override
    public void run() {
        while (true){
            System.out.println(this);
            if(--decrement == 0){
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleRunnable();
        }
    }
}
