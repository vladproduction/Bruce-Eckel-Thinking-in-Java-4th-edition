package com.vladproduction.concurrency.executors;

import com.vladproduction.concurrency.thread_runnable_demo.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {

        // Constructor argument is number of threads:
        ExecutorService exec = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }

        exec.shutdown();
    }
}