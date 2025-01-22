package com.vladproduction.concurrency.atomicity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * provided solution:
 * 1)AtomicInteger as atomic shared value;
 * 2)method become: synchronized int nextSerialNumber();
 * */
public class SerialNumberGeneratorSolution {
    private static AtomicInteger serialNumber = new AtomicInteger(0); //AtomicInteger as atomic shared value

    public static synchronized int nextSerialNumber() { //method is synchronized
        return serialNumber.incrementAndGet();
    }

    public static void main(String[] args) {

        System.out.print(nextSerialNumber());//1

        Thread thread1 = new Thread(() -> {
            System.out.print(nextSerialNumber());//2
        });

        Thread thread2 = new Thread(() -> {
            System.out.print(nextSerialNumber());//3
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(nextSerialNumber());//4
    }

}
