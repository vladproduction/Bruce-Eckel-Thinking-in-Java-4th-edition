package com.vladproduction.concurrency.thread_runnable_simple_demo;

public class SimpleThread extends Thread {
    private int decrement = 5;
    private static int threadCount = 0;

    //when create this object thread starting
    public SimpleThread() {
        //save name of the thread:
        super(Integer.toString(++threadCount));
        start();
    }

    //toString()
    public String toString() {
        return "#" + getName() + "(" + decrement + ")";
    }

    //Override run()
    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--decrement == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program started!");
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
        Thread.sleep(500);
        System.out.println("Program finished!");

    }
}
