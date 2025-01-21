package com.vladproduction.concurrency.sleeping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class to execute cars racing
 *
 * Executes a mixture of regular cars and fast cars,
 *      all starting with the same distance of 20.
 *      Each car runs its racing simulation concurrently.
 * */
public class CarRaceApp {
    public static void main(String[] args) {
        //create executors
        ExecutorService executorService = Executors.newCachedThreadPool(); //create thread pool

        //create a mix regular and fast cars:
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Car(20));
            executorService.execute(new FastCar(20));
        }

        executorService.shutdown();

    }
}

//Each car's status printed as they race toward the finish line, with their progress displayed until they finish;
//The random nature of the distance reduction will provide varied outputs each time;

