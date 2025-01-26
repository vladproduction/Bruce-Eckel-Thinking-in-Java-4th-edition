package com.vladproduction.concurrency.locking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant Locks:
 * - The class uses a ReentrantLock, which is a concrete implementation of the Lock interface.
 *   It allows threads to lock and unlock resources, providing finer control over concurrency compared to the 'synchronized' keyword.
 * - A reentrant lock allows the same thread to acquire the lock multiple times without causing a deadlock.
 * */
public class AttemptLocking {

    private ReentrantLock lock = new ReentrantLock();
    private CountDownLatch latch = new CountDownLatch(1);

    //demonstrate non-blocking attempts to acquire a lock
    //useful for avoiding deadlock scenarios where a thread might be blocked indefinitely while waiting for a lock
    public void untimed(){
        boolean captured = lock.tryLock(); //will return immediately, indicating whether the lock was captured or not
        try{
            System.out.println("tryLock(): " + captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }

    public void timed(){
        boolean captured = false;
        try{
            captured = lock.tryLock(2, TimeUnit.SECONDS); //will wait for the specified time before giving up
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        try{
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();

        new Thread(){
            {
                setDaemon(true);
            }
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
                al.latch.countDown(); // Signal that the lock is acquired
            }
        }.start();

        try {
            al.latch.await(); // Wait for the signal from the daemon thread
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        al.untimed();
        al.timed();
    }

}
/*The example shows how to create a new thread and indicates it as a daemon thread.
Daemon threads are background threads that do not prevent the JVM from exiting when the program finishes,
as they are terminated when all user threads (non-daemon threads) finish executing.*/
