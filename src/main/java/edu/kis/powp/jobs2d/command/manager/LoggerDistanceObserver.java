package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class LoggerDistanceObserver implements Subscriber {

    private double headDistance = 0;
    private double operatingDistance = 0;

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void getDistances(double headDistance, double operatingDistance){
        this.headDistance = headDistance;
        this.operatingDistance = operatingDistance;
    }

    @Override
    public void update() {
        logger.info("HeadDistance: " + this.headDistance);
        logger.info("OperatingDistance: " + this.operatingDistance);
    }

    public String toString() {
        return "Logger Distance Observer";
    }
}
