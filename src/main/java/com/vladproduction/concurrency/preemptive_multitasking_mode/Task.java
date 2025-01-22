package com.vladproduction.concurrency.preemptive_multitasking_mode;

class Task implements Runnable {
    private String name;
    private int executionTime; // total time this task needs to execute in milliseconds
    private int timeSlice; // time quantum for this task

    public Task(String name, int executionTime, int timeSlice) {
        this.name = name;
        this.executionTime = executionTime;
        this.timeSlice = timeSlice;
    }

    @Override
    public void run() {
        // Simulate executing the task with time quanta
        while (executionTime > 0) {
            // Calculate the actual time to execute: either the remaining time or the time slice
            int timeToExecute = Math.min(executionTime, timeSlice);
            System.out.println(name + " is executing for " + timeToExecute + " ms.");
            try {
                Thread.sleep(timeToExecute); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            executionTime -= timeToExecute; // Reduce remaining execution time
            System.out.println(name + " has " + executionTime + " ms left.");
        }
        System.out.println(name + " has completed execution.");
    }
}
