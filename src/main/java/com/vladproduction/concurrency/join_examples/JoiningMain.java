package com.vladproduction.concurrency.join_examples;

/**
 * Joiner - a thread that waits for the Sleeper thread to wake up, calling the latter's join() method.
 * In the main() method, each Joiner object is assigned a Sleeper, and in the program's output we can check
 * if the Sleeper was interrupted or terminated normally,
 * the Joiner terminates along with the Sleeper thread.
 * */
public class JoiningMain {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper(1590, "Sleepy"),
                grumpy = new Sleeper(1500, "Grumpy");
        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}
