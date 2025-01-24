package com.vladproduction.concurrency.exercises;

/**
 * Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call
 * yield( ). Repeat this three times, and then return from run( ). Put a startup message in the
 * constructor and a shutdown message when the task terminates. Create a number of these
 * tasks and drive them using threads.
 */
public class Exercise1 {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new TaskRunnable("Task 1"));
        Thread thread2 = new Thread(new TaskRunnable("Task 2"));
        Thread thread3 = new Thread(new TaskRunnable("Task 3"));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nAll task are finished!");


    }
}

class TaskRunnable implements Runnable {
    private String name;
    public TaskRunnable(String name) {
        this.name = name;
        System.out.println(name + " has started...");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(name + " is running iteration: " + (i + 1));
            Thread.yield();
        }
        System.out.println(name + " has finished!");

    }

}

