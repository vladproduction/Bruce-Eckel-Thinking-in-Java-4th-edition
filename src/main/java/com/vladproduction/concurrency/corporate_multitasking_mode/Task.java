package com.vladproduction.concurrency.corporate_multitasking_mode;

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // Simulate task execution
        System.out.println(name + " is executing.");
        try {
            // Simulating work by sleeping for a short duration
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(name + " is yielding control.");
    }
}
