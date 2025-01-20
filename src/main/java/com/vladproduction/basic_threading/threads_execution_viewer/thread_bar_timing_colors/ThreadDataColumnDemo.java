package com.vladproduction.basic_threading.threads_execution_viewer.thread_bar_timing_colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadDataColumnDemo extends JFrame {
    private static final int THREAD_COUNT = 5;

    private ThreadWorker[] threadWorkers;
    private long[] executionTimes = new long[THREAD_COUNT];
    private boolean running = false;

    private ChartPanel chartPanel;
    private JButton startButton;
    private JButton stopButton;

    public ThreadDataColumnDemo() {
        setTitle("Thread Execution Times Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chartPanel = new ChartPanel(executionTimes);
        add(chartPanel, BorderLayout.CENTER);

        startButton = new JButton("Start Threads");
        stopButton = new JButton("Stop Threads");
        stopButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startThreads();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopThreads();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startThreads() {
        running = true;

        // Initialize the threadWorkers array
        threadWorkers = new ThreadWorker[ThreadsConstants.THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            executionTimes[i] = 0; // Reset execution times
            threadWorkers[i] = new ThreadWorker(i, executionTimes, chartPanel);
            threadWorkers[i].start();
        }

        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    private void stopThreads() {
        running = false;
        for (ThreadWorker worker : threadWorkers) {
            if (worker != null) {
                worker.interrupt(); // Stop the thread
            }
        }
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        chartPanel.repaint(); // Update the chart after stopping
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThreadDataColumnDemo demo = new ThreadDataColumnDemo();
            demo.setVisible(true);
        });
    }
}
