package com.vladproduction.concurrency.threads_execution_viewer_gui.thread_bar_top_scaling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadDataColumnDemo extends JFrame {
    private ThreadWorker[] threadWorkers;
    private long[] executionTimes = new long[ThreadConstants.THREAD_COUNT];
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

        // Initialize thread workers
        threadWorkers = new ThreadWorker[ThreadConstants.THREAD_COUNT];
        
        for (int i = 0; i < ThreadConstants.THREAD_COUNT; i++) {
            executionTimes[i] = 0; // Reset execution times
            threadWorkers[i] = new ThreadWorker(i, executionTimes, chartPanel);
            threadWorkers[i].start();
        }

        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        // Timer to update the chart every 5 seconds
        Timer timer = new Timer(5000, e -> chartPanel.updateChart());
        timer.start();
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
