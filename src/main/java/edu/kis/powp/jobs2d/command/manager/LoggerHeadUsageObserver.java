package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageManager;
import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageFeature;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class LoggerHeadUsageObserver implements Subscriber {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HeadUsageManager headUsageManager;

    public LoggerHeadUsageObserver(HeadUsageManager headUsageManager) {
        this.headUsageManager = headUsageManager;
    }

    public void update() {
        logger.info("Head distance: " + headUsageManager.getHeadDistance() + " units");
        logger.info("Op.  distance: " + headUsageManager.getOperationDistance() + " units");
    }

    public String toString() {
        return "Logger HeadUsage Observer";
    }

}