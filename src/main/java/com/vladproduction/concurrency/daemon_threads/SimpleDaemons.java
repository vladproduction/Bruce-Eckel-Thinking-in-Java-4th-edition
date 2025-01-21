package com.vladproduction.concurrency.daemon_threads;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable{
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SimpleDaemons());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("Print all daemons started");
        TimeUnit.MILLISECONDS.sleep(200); //pause main() to be able to see all daemons threads are started and running
    }
    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (InterruptedException e){
            System.out.println("sleep() - interrupted");
        }
    }


}
