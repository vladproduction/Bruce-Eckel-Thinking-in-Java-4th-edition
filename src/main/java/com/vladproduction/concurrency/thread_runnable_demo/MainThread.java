package com.vladproduction.concurrency.thread_runnable_demo;

public class MainThread {
    public static void main(String[] args) {

        //the task’s run( ) is not driven by a separate thread; it is simply
        //called directly in main( ) (actually, this is using a thread: the one that is always allocated for main( )):
        LiftOff launch = new LiftOff();
        launch.run();

        //#0(9); #0(8); #0(7); #0(6); #0(5); #0(4); #0(3); #0(2); #0(1); #0(Liftoff!); Waiting for LiftOff

        //traditional way to turn a Runnable object into a working task is to hand it to a Thread
        //constructor
        Thread thread = new Thread(launch);
        thread.start();
        System.out.print("Waiting for LiftOff\n");

        /*  Waiting for LiftOff
            #0(9); #0(8); #0(7); #0(6); #0(5); #0(4); #0(3); #0(2); #0(1); #0(Liftoff!);*/

        //In this case, a single thread (main( )), is creating all the LiftOff threads. If you have multiple threads creating LiftOff
        //threads, however, it is possible for more than one LiftOff to have the same id.
        for(int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");

    }
}