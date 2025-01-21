package com.vladproduction.concurrency.livelock;

class TrafficLight {

    private boolean isGreen;

    public TrafficLight() {
        isGreen = false; // Initially, the light is red
    }

    public synchronized void turnGreen() {
        isGreen = true;
        System.out.println("Traffic light is green!");
    }

    public synchronized void turnRed() {
        isGreen = false;
        System.out.println("Traffic light is red!");
    }

    public synchronized boolean isGreen() {
        return isGreen;
    }
}
