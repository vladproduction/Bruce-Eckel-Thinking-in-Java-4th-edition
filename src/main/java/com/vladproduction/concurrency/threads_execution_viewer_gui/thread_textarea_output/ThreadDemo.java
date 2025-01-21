package com.vladproduction.concurrency.threads_execution_viewer_gui.thread_textarea_output;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadDemo extends JFrame {

    private JTextArea textArea;
    private JButton startBtn;
    private JButton stopBtn;
    private WorkerThread[] workers;

    public ThreadDemo(){
        setTitle("Thread Demo");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        startBtn = new JButton("Start Threads");
        stopBtn = new JButton("Stop Threads");
        stopBtn.setEnabled(false);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startThreads();
            }
        });

        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopThreads();
            }
        });

        panel.add(startBtn);
        panel.add(stopBtn);
        add(panel, BorderLayout.SOUTH);

    }

    //private methods to start and stop threads for our listeners as buttons
    private void startThreads() {
        textArea.setText("");
        workers = new WorkerThread[5]; // create 5 worker threads

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new WorkerThread("Thread-" + (i + 1), textArea);
            workers[i].start();
        }

        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);
    }

    private void stopThreads() {
        for (WorkerThread thread : workers) {
            if (thread != null) {
                thread.interrupt(); // Stop the thread
            }
        }

        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
    }

}
