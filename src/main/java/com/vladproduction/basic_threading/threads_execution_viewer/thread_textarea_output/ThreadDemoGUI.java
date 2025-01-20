package com.vladproduction.basic_threading.threads_execution_viewer.thread_textarea_output;

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
