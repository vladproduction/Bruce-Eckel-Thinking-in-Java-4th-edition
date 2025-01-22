package com.vladproduction.concurrency.atomicity;

/**
 * Potential Issues
 *
 * Race Conditions: Although the output in this specific run is predictable because of the join(), in a more complex or less controlled
 * multithreading scenario, multiple threads could call nextSerialNumber() simultaneously.
 * This might lead to race conditions where two or more threads read the same value of serialNumber before any of them can increment
 * and write it back, resulting in duplicate serial numbers.
 *
 * Non-Atomic Increment: The serialNumber++ operation is not atomic. In a high multithreading environment without synchronization mechanisms,
 * the value of serialNumber could become inconsistent.
 * */
public class SerialNumberGenerator {
    private static int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(200);
        System.out.print(nextSerialNumber());//0

        Thread.sleep(200);
        System.out.print(nextSerialNumber());//1


        Thread thread1 = new Thread(() -> {
            System.out.print(nextSerialNumber());//2
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.print(nextSerialNumber());//3
        });

        thread1.start();
        Thread.sleep(200);
        thread2.start();

        try {
            thread1.join();
            Thread.sleep(2000);
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(200);
        System.out.print(nextSerialNumber());//4
    }

}
