package com.vladproduction.concurrency.daemon_threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DaemonThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public DaemonThreadFactory(String name) {
        namePrefix = name + "-daemon-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + threadNumber.getAndIncrement());
        thread.setDaemon(true); // Set the thread as a daemon
        return thread;
    }
}
