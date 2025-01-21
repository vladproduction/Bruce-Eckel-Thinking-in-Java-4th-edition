package com.vladproduction.concurrency.thread_yield;

/**
 * Implement the Runnable interface. In the run() method, print a message, then yield().
 * Repeat three times and return control from run().
 * Print the initial message in the constructor, and print the final message when finished.
 * Create several tasks and organize their execution using threads.
 * */
public class MyRunnable implements Runnable{

    private String taskName;

    public MyRunnable(String taskName) {
        this.taskName = taskName;
        System.out.println(taskName + " has been created.");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(taskName + " is running: iteration " + (i + 1));
            Thread.yield();
        }
        System.out.println(taskName + " has finished execution.");
    }
}
