package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.usage.UsageManager;
import edu.kis.powp.observer.Subscriber;
import java.util.logging.Logger;

public class LoggerDistanceObserver implements Subscriber {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final UsageManager usageManager;

    public LoggerDistanceObserver(UsageManager usageManager){
        this.usageManager = usageManager;
    }

    @Override
    public void update() {
        logger.info("HeadDistance: " + usageManager.getHeadDistance());
        if(usageManager.canOperate()) logger.info("OperatingDistance: " + usageManager.getOperatingDistance());
        else logger.info("WARNING: The device requires service !!!");
    }

    public String toString() {
        return "Logger Distance Observer";
    }
}
