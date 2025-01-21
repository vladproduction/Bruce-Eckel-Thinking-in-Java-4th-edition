package com.vladproduction.concurrency.thread_runnable_demo;

public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!!!") + "); ";
    }

    public void run() {
        System.out.println("Lift "  + id + " started...");
        while (countDown-- > 0) {
            System.out.print(status() + "\n");
            Thread.yield();
        }
    }
}