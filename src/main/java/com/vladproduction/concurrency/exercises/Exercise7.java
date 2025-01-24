package com.vladproduction.concurrency.exercises;

/**
 * Exercise 7: (2) Experiment with different sleep times in Daemons.java to see what
 * happens.
 * */
public class Exercise7 {
    public static void main(String[] args) {
        // Create a few daemon threads
        for (int i = 0; i < 5; i++) {
            Thread daemonThread = new Thread(new DaemonThread(i));
            daemonThread.setDaemon(true); // Set thread as daemon
            daemonThread.start();
        }

        System.out.println("Press ctrl + c to stop the main thread...");

        // Keep the main thread running for some time
        try {
            Thread.sleep(5000); // Main thread sleeps for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread completed.");
    }

    static class DaemonThread implements Runnable {
        private int id;

        public DaemonThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // Vary the sleep time to see the effect
                    int sleepTime = (id + 1) * 1000; // Different sleep times (1s, 2s, 3s, ...)
                    System.out.println("Daemon thread " + id + " sleeping for " + sleepTime + " ms.");
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread " + id + " interrupted.");
                }
            }
        }
    }
}
