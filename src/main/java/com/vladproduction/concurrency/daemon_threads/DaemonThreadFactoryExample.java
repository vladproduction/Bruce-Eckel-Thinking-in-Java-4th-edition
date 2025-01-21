package com.vladproduction.concurrency.daemon_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DaemonThreadFactoryExample {
    public static void main(String[] args) throws InterruptedException {

        DaemonThreadFactory factory = new DaemonThreadFactory("MyDaemon");
        ExecutorService executor = Executors.newFixedThreadPool(2, factory);

        executor.submit(() -> {
            while (true) {
                System.out.println("Daemon thread is running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread.sleep(2000);

        executor.shutdown();

        /* output till main is not terminated:
        Daemon thread is running...
        Daemon thread is running...*/
    }
}
