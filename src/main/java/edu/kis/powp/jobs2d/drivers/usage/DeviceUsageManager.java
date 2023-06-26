package edu.kis.powp.jobs2d.drivers.usage;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;

public class DeviceUsageManager {
    private double headDistance = 0;
    private double operatingDistance = 0;

    private int xLastPosition = 0;
    private int yLastPosition = 0;

    private final Publisher distanceChangePublisher = new Publisher();

    public DeviceUsageManager() {
        this.distanceChangePublisher.addSubscriber(DriverFeature.getDriverUsageObserver());
    }

    public double calculateDistance(int x, int y){
        double distance = Math.sqrt(Math.pow(y - yLastPosition, 2) + Math.pow(x - xLastPosition, 2));
        return distance;
    }

    public void calculateMovingDistance(int x, int y){
        headDistance += calculateDistance(x, y);
        xLastPosition = x;
        yLastPosition = y;
        distanceChangePublisher.notifyObservers();
    }

    public void calculateOperatingDistance(int x, int y){
        double distance = calculateDistance(x, y);
        headDistance += distance;
        operatingDistance += distance;
        xLastPosition = x;
        yLastPosition = y;
        distanceChangePublisher.notifyObservers();
    }

    public double getHeadDistance() {
        return headDistance;
    }

    public void setHeadDistance(double headDistance) {
        this.headDistance = headDistance;
    }

    public double getOperatingDistance() {
        return operatingDistance;
    }

    public void setOperatingDistance(double operatingDistance) {
        this.operatingDistance = operatingDistance;
    }

    public Publisher getDistanceChangePublisher() {
        return distanceChangePublisher;
    }
}
