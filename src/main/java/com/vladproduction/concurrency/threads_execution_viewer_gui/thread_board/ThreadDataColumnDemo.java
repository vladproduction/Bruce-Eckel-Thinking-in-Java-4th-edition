package com.vladproduction.concurrency.threads_execution_viewer_gui.thread_board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ThreadDataColumnDemo extends JFrame {
    private static final int THREAD_COUNT = 5;
    
    private ThreadWorker[] threadWorkers;
    private long[] executionTimes = new long[THREAD_COUNT];
    private boolean running = false;

    private JPanel chartPanel;
    private JButton startButton;
    private JButton stopButton;

    public ThreadDataColumnDemo() {
        setTitle("Thread Execution Times Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart(g);
            }
        };
        chartPanel.setPreferredSize(new Dimension(600, 300));

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

        add(chartPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startThreads() {
        running = true;
        threadWorkers = new ThreadWorker[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            executionTimes[i] = 0; // Reset execution times
            threadWorkers[i] = new ThreadWorker(i);
            threadWorkers[i].start();
        }

        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        
        // Repaint the chart periodically
        Timer timer = new Timer(100, e -> chartPanel.repaint());
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
    }

    private void drawChart(Graphics g) {
        int width = chartPanel.getWidth() / THREAD_COUNT;
        int maxHeight = chartPanel.getHeight();
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            g.setColor(Color.BLUE);
            // Normalize execution time for display
            int height = (int) ((executionTimes[i] / 1000.0) * maxHeight / 10); // Adjust max time scale
            height = Math.min(height, maxHeight); // Cap to panel height
            
            g.fillRect(i * width, maxHeight - height, width - 2, height); // Create bar
            g.setColor(Color.BLACK);
            g.drawRect(i * width, maxHeight - height, width - 2, height); // Draw border
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThreadDataColumnDemo demo = new ThreadDataColumnDemo();
            demo.setVisible(true);
        });
    }

    class ThreadWorker extends Thread {
        private int index;

        public ThreadWorker(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            Random random = new Random();

            while (running) {
                // Simulated work with random time
                long workTime = random.nextInt(1000) + 500; // Work time 500 to 1500 ms
                try {
                    Thread.sleep(workTime); // Simulate work
                } catch (InterruptedException e) {
                    break; // Interrupt signal received
                }
                executionTimes[index] += workTime; // Accumulate execution time
            }
        }
    }
}
