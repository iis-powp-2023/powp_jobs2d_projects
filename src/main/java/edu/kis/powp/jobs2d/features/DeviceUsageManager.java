package edu.kis.powp.jobs2d.features;

import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

public class DeviceUsageManager {
    private static double headDistance = 0;
    private static double operatingDistance = 0;

    private int xLastPosition = 0;
    private int yLastPosition = 0;

    private final Publisher changePublisher = new Publisher();

    private double calculateDistance(int x, int y){
        double distance = Math.sqrt(Math.pow(y - yLastPosition, 2) + Math.pow(x - xLastPosition, 2));
        xLastPosition = x;
        yLastPosition = y;
        return distance;
    }

    public void calculateMovingDistance(int x, int y){
        headDistance += calculateDistance(x, y);
        changePublisher.notifyObservers();
    }

    public void calculateOperatingDistance(int x, int y){
        double distance = calculateDistance(x, y);
        headDistance += distance;
        operatingDistance += distance;
        changePublisher.notifyObservers();
    }

    public synchronized void addSubscriber(Subscriber subscriber) {
        changePublisher.addSubscriber(subscriber);
    }

    public static double getHeadDistance() {
        return headDistance;
    }

    public static double getOperatingDistance() {
        return operatingDistance;
    }
}
