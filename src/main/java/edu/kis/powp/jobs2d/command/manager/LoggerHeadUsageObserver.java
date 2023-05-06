package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageFeature;
import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageSingleton;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class LoggerHeadUsageObserver implements Subscriber {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HeadUsageFeature headUsageFeature;

    public LoggerHeadUsageObserver() {
        this.headUsageFeature = HeadUsageSingleton.setupHeadUsageFeature(5000);
    }

    public void update() {
        logger.info("Head distance: " + headUsageFeature.getHeadDistance() + " units");
        logger.info("Op.  distance: " + headUsageFeature.getOperationDistance() + " units");
    }

    public String toString() {
        return "Logger HeadUsage Observer";
    }

}