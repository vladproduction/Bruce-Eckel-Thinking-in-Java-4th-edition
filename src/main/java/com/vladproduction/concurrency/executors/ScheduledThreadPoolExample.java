package com.vladproduction.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled Thread Pool: Allows scheduling of tasks at fixed rates or with delays.
 * */
public class ScheduledThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("Periodic task executed: " + Thread.currentThread().getName());
        }, 0, 1, TimeUnit.SECONDS); // Initial delay of 0 seconds, repeat every 2 seconds

        // To stop the periodic task after 10 seconds
        scheduledExecutorService.schedule(() -> {
            scheduledExecutorService.shutdown();
            System.out.println("Scheduler stopped.");

            try{
                //waiting to currently executing task to terminate
                if(!scheduledExecutorService.awaitTermination(2, TimeUnit.SECONDS)){
                    scheduledExecutorService.shutdown();
                }
            }catch (InterruptedException e){
                scheduledExecutorService.shutdown();
                Thread.currentThread().interrupt(); //preface interrupted flag
                System.out.println("Scheduling interrupted during shutdown!");
            }

        }, 5, TimeUnit.SECONDS);


    }
}
