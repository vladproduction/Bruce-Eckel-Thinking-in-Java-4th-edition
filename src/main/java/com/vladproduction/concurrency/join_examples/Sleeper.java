package com.vladproduction.concurrency.join_examples;

public class Sleeper extends Thread {
    private int sleepTime;

    public Sleeper(int sleepTime, String name) {
        super(name);
        this.sleepTime = sleepTime; //fall asleep by setting time in constructor
        start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            //information about the interruption along with the return value of the isInterrupted() method
            //This flag is cleared when handling an exception, so inside a catch block the result will always be false
            System.out.println(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}
