package edu.kis.powp.jobs2d.usage;

import edu.kis.powp.observer.Publisher;

public class UsageManager {
    private double headDistance = 0;
    private double operatingDistance = 0;

    private int xLastPosition = 0;
    private int yLastPosition = 0;

    private double serviceInterval = 10000;
    private int maxServiceInterval = 10000;

    private boolean isOperational = true;

    private final Publisher distanceChangePublisher = new Publisher();
    private final Publisher windowChangePublisher = new Publisher();

    public double getServiceInterval(){
        return this.serviceInterval;
    }
    public void setServiceInterval(int serviceInterval){ this.serviceInterval = serviceInterval; }
    public int getMaxServiceInterval(){ return this.maxServiceInterval; }
    public void setMaxServiceInterval(int maxServiceInterval){
        this.maxServiceInterval = maxServiceInterval;
        if(serviceInterval > maxServiceInterval){
            serviceInterval = maxServiceInterval;
        }
    }

    public Boolean canOperate(){ return this.isOperational; }

    public double getDeviceUsage(){
        return serviceInterval / maxServiceInterval;
    }

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
        if(distance > this.serviceInterval)
            this.isOperational = false;
        else{
            this.isOperational = true;
            this.serviceInterval -= distance;
            operatingDistance += distance;
        }
        headDistance += distance;
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
