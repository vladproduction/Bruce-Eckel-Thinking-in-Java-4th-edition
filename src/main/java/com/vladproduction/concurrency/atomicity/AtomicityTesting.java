package com.vladproduction.concurrency.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The issue lies in the structure of the run() method and how the getValue() method is called:
 *
 * The run() method calls evenIncrement() in an infinite loop without any breaks or synchronization with the main thread.
 * This means that if it is being incremented and the main thread simultaneously calls getValue(),
 * it could potentially read i at an intermediate state between the two increments.
 *
 * The main method continuously calls getValue(), which reads the value of i. Since it is not synchronized, this call could happen at any time,
 * including just after it has been incremented to an odd value but before it has been incremented again in evenIncrement().
 *
 * If the main thread reads i after the first increment but before the second increment completes, it may observe an odd value.
 * */
public class AtomicityTesting implements Runnable{
    private int i = 0;

    public synchronized int getValue(){
        return i;
    }

    private synchronized void evenIncrement(){
        i++;
        i++;
    }

    public void run(){
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicityTesting at = new AtomicityTesting();

        executorService.execute(at);

        //printing results:
        while (true){
            int val = at.getValue();
            if(val % 2 != 0){
                System.out.print(val);
                System.exit(0);
            }
        }
    }

    /**
     * Lack of Control: There is no synchronization between the getValue() calls in the main thread and the evenIncrement()
     * method calls in the worker thread.
     * The calls to getValue() could result in the main thread reading i when it is in an inconsistent state because the two
     * operations (the increments) are separated in time by multiple invocations of evenIncrement().
     * */

}
