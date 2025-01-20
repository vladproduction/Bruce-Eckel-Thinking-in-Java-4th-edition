package com.vladproduction.basic_threading.threads_execution_viewer.thread_textarea_output;

import javax.swing.*;

public class WorkerThread extends Thread{

    private JTextArea textArea;

    public WorkerThread(String name, JTextArea textArea) {
        super(name);
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()){
                //simulate work by using sleep();
                int sleepTime = (int) (Math.random() * 1000);
                Thread.sleep(sleepTime);

                //output thread name and sleep time;
                SwingUtilities.invokeLater(()->{
                    textArea.append(getName() + " performed work for " + sleepTime + " ms\n");
                });
            }
        }catch (InterruptedException e){
            //Thread interrupted, exit gracefully
        }finally {
            SwingUtilities.invokeLater(()->{
                textArea.append(getName() + " has stopped\n");
            });
        }
    }
}
