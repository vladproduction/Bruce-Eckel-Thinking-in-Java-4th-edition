package com.vladproduction.concurrency.livelock;

class Car extends Thread {
    private String name;
    private TrafficLight trafficLight;

    public Car(String name, TrafficLight trafficLight) {
        this.name = name;
        this.trafficLight = trafficLight;
    }

    @Override
    public void run() {
        while (true) {
            if (trafficLight.isGreen()) {
                System.out.println(name + " is passing through the intersection.");
                trafficLight.turnRed(); // Traffic light turns red after the car passes
            } else {
                System.out.println(name + " is waiting for the green light.");
                trafficLight.turnGreen(); // Traffic light turns green for the next car
            }
            // Simulate some processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
