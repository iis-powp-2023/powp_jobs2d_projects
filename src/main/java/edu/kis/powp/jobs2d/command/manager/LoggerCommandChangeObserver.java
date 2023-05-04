package edu.kis.powp.jobs2d.command.manager;

import java.util.logging.Logger;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.InkUsage.InkUsageFeature;
import edu.kis.powp.jobs2d.features.InkUsage.InkUsageFeatureManager;
import edu.kis.powp.observer.Subscriber;

public class LoggerCommandChangeObserver implements Subscriber {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void update() {
        InkUsageFeature inkUsageFeature = InkUsageFeatureManager.getInkUsageFeature();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        logger.info("Head distance: "+Double.toString(inkUsageFeature.getHeadDistance())+" units");
        logger.info("Op.  distance: "+Double.toString(inkUsageFeature.getOperationDistance())+" units");
        logger.info("Current command set to: " + command.toString());

     }



    public String toString() {
        return "Logger Command Change Observer";

    }

}
