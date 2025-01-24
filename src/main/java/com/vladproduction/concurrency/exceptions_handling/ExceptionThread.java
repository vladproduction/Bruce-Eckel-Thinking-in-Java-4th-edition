package com.vladproduction.concurrency.exceptions_handling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {

//        new Thread(new ExceptionThread()).start();

        //or we can create pool of threads to execute task
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());

    }
}
