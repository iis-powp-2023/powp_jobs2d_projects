package edu.kis.powp.jobs2d.usage;

import edu.kis.powp.observer.Publisher;

public class UsageManager {
    private double headDistance = 0;
    private double operatingDistance = 0;

    private int xLastPosition = 0;
    private int yLastPosition = 0;

    private int serviceInterval = 10000;
    private int maxServiceInterval = 10000;

    public int getServiceInterval(){
        return this.serviceInterval;
    }
    public int getMaxServiceInterval(){
        return this.maxServiceInterval;
    }

    private final Publisher distanceChangePublisher = new Publisher();
    private final Publisher windowChangePublisher = new Publisher();

    private double calculateDistance(int x, int y){
        double distance = Math.sqrt(Math.pow(y - yLastPosition, 2) + Math.pow(x - xLastPosition, 2));
        xLastPosition = x;
        yLastPosition = y;
        return distance;
    }

    public void calculateMovingDistance(int x, int y){
        headDistance += calculateDistance(x, y);
        distanceChangePublisher.notifyObservers();
        windowChangePublisher.notifyObservers();
    }

    public void calculateOperatingDistance(int x, int y){
        double distance = calculateDistance(x, y);
        headDistance += distance;
        operatingDistance += distance;
        this.serviceInterval -= distance;
        distanceChangePublisher.notifyObservers();
        windowChangePublisher.notifyObservers();
    }

    public double getHeadDistance() {
        return headDistance;
    }

    public double getOperatingDistance() {
        return operatingDistance;
    }

    public Publisher getDistanceChangePublisher() {
        return distanceChangePublisher;
    }
    public Publisher getWindowChangePublisher() {
        return windowChangePublisher;
    }
}
