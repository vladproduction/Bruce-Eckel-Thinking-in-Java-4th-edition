package com.vladproduction.concurrency.sharing_data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * idiom: immediately after calling lock(), place a try-finally command with unlock()
 * in the 'finally' section - this is the only way to guarantee that the lock will be released in any case;
 *
 * the try block must contain a 'return' statement to ensure that the unlock() statement is not executed too early
 * and the data does not become available to another task;
 * */
public class MutexEvenGenerator extends IntGenerator{

    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}

/*      Lock objects are usually used only in special situations.
        For example, with the synchronized keyword, it is not possible to handle a failed attempt to acquire a lock or
        to interrupt attempts to acquire a lock after a specified period of time has elapsed -
        in such cases, it is necessary to use the concurrent library;*/
