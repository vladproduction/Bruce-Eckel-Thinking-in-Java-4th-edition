package com.vladproduction.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * - Example of how to schedule periodic tasks with difference intervals;
 * - After 20 seconds program gracefully shutdown;
 * - The awaitTermination method waits for up to 5 seconds for all tasks to complete.
 *      If tasks are still running after this period, shutdownNow() will be called to force a shutdown.
 * - If interrupted while waiting for tasks to complete, it catches the InterruptedException,
 *      calls shutdownNow(), and preserves the interrupt status of the thread.
 * */
public class ScheduledThreadPoolAdvancedExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        // Schedule a periodic task that runs every 4 seconds
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed: " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        }, 0, 4, TimeUnit.SECONDS);

        // Schedule another periodic task that runs every 6 seconds
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Another periodic task executed: " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        }, 0, 6, TimeUnit.SECONDS);

        //gracefully shutdown after 20 seconds:
        scheduledExecutorService.schedule(()->{
            scheduledExecutorService.shutdown();
            System.out.println("Scheduler stopped.");
            try{
                //wait to currently executing tasks to terminate
                if(!scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)){
                    scheduledExecutorService.shutdown(); // Force shutdown if tasks did not finish
                }
                System.out.println("All tasks finished.");
            }catch (InterruptedException e){
                scheduledExecutorService.shutdown();
                Thread.currentThread().interrupt();
                System.out.println("Scheduler interrupted during shutdown.");
            }
        }, 20, TimeUnit.SECONDS);

    }
}
