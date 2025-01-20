package com.vladproduction.basic_threading.thread_yield;

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
