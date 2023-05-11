package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.DeviceUsageManager;
import edu.kis.powp.observer.Subscriber;
import java.util.logging.Logger;

public class LoggerDistanceObserver implements Subscriber {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final DeviceUsageManager deviceUsageManager;

    public LoggerDistanceObserver(DeviceUsageManager deviceUsageManager){
        this.deviceUsageManager = deviceUsageManager;
    }

    @Override
    public void update() {
        logger.info("HeadDistance: " + deviceUsageManager.getHeadDistance());
        logger.info("OperatingDistance: " + deviceUsageManager.getOperatingDistance());
    }

    public String toString() {
        return "Logger Distance Observer";
    }
}
