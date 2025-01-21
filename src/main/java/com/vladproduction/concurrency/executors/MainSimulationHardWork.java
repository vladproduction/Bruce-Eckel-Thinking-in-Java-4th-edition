package com.vladproduction.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainSimulationHardWork {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(1); //static method is changeable for different types of work

        for (int i = 0; i < 10000000; i++) {
            Runnable runnable = () -> {
                String s = "";
                while (true) {
                    s += 'a';
                }
            };

            service.execute(runnable);
        }

    }
}
