package com.vladproduction.concurrency.sharing_data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int value = generator.next();
            if (value % 2 != 0) {
                System.out.println(value + " is not even.");
                generator.cancel();
            }
        }
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control+F2 to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(gp, i));
        }
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
