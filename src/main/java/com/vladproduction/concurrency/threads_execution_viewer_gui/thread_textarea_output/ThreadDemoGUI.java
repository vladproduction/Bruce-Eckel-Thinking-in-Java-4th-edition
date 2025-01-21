package com.vladproduction.concurrency.threads_execution_viewer_gui.thread_textarea_output;

import javax.swing.*;

/**
 * simulating how a thread scheduler works in a multithreading application
 * */
public class ThreadDemoGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            ThreadDemo threadDemo = new ThreadDemo();
            threadDemo.setVisible(true);
        });
    }
}
