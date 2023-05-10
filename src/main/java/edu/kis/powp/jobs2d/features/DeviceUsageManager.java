package edu.kis.powp.jobs2d.features;

import java.util.logging.Logger;

public class DeviceUsageManager {
    private double headDistance = 0;
    private double operatingDistance = 0;

    private int xLastPosition = 0;
    private int yLastPosition = 0;

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private void update(){
        logger.info("HeadDistance: " + this.headDistance);
        logger.info("OperatingDistance: " + this.operatingDistance);
    }

    private double calculateDistance(int x, int y){
        double distance = Math.sqrt(Math.pow(y - yLastPosition, 2) + Math.pow(x - xLastPosition, 2));
        xLastPosition = x;
        yLastPosition = y;
        return distance;
    }

    public void calculateMovingDistance(int x, int y){
        headDistance += calculateDistance(x, y);
        update();
    }

    public void calculateOperatingDistance(int x, int y){
        double distance = calculateDistance(x, y);
        headDistance += distance;
        operatingDistance += distance;
        update();
    }

    public double getHeadDistance() {
        return headDistance;
    }

    public double getOperatingDistance() {
        return operatingDistance;
    }
}
