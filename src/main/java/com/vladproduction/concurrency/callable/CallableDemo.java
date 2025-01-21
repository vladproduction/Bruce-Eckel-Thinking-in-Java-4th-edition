package com.vladproduction.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors = " + processors);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        
        //add submitted task
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        
        //get result from list
        for (Future<String> result : results) {
            try{
                System.out.println(result.get());
            }catch (InterruptedException e){
                System.out.println(e);
                return;
            }catch (ExecutionException e){
                System.out.println(e);
            }finally {
                executorService.shutdown();
            }
        }

    }
}
