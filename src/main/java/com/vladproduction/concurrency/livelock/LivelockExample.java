package com.vladproduction.concurrency.livelock;

public class LivelockExample {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        Car car1 = new Car("Car 1", trafficLight);
        Car car2 = new Car("Car 2", trafficLight);

        car1.start();
        car2.start();
    }
}
