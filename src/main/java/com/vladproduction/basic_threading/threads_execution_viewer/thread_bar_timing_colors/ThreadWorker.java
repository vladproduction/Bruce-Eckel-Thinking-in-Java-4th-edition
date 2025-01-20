package com.vladproduction.basic_threading.threads_execution_viewer.thread_bar_timing_colors;

import java.util.Random;

public class ThreadWorker extends Thread {
    private int index;
    private long[] executionTimes;
    private ChartPanel chartPanel;

    public ThreadWorker(int index, long[] executionTimes, ChartPanel chartPanel) {
        this.index = index;
        this.executionTimes = executionTimes;
        this.chartPanel = chartPanel;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (!isInterrupted()) {
            // Simulated work with random time
            long workTime = random.nextInt(1000) + 500; // Work time 500 to 1500 ms
            try {
                Thread.sleep(workTime); // Simulate work
            } catch (InterruptedException e) {
                break; // Interrupt signal received
            }
            executionTimes[index] += workTime; // Accumulate execution time
            chartPanel.repaint(); // Repaint the chart
        }
    }
}
