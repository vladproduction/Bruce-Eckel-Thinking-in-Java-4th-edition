package com.vladproduction.concurrency.thread_yield;

public class ThreadExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable("Task#1"));
        Thread thread2 = new Thread(new MyRunnable("Task#2"));
        Thread thread3 = new Thread(new MyRunnable("Task#3"));

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("All tasks by MyRunnable has been completed!");
    }
}
