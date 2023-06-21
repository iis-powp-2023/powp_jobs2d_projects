package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.decorator.DriverDecorator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ExtensionsManager implements FeatureObject {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Level loggerLevel;
    private boolean isLoggerRunning = true;
    private static boolean isDistanceLogRunning = true;

    @Override
    public void setup(Application application) {
        application.addComponentMenu(ExtensionsManager.class,"Extensions");
        application.addComponentMenuElementWithCheckBox(ExtensionsManager.class, "Logger", (ActionEvent e) -> toggleLogger(), true);
        application.addComponentMenuElementWithCheckBox(ExtensionsManager.class, "Distance log",(ActionEvent e) -> toggleDistanceLog(), true);
    }
    public void toggleLogger(){
        if(isLoggerRunning) loggerLevel = logger.getLevel();
        logger.setLevel(isLoggerRunning ? Level.OFF : loggerLevel);
        isLoggerRunning = !isLoggerRunning;
    }
    public void toggleDistanceLog(){
        isDistanceLogRunning = !isDistanceLogRunning;
    }
    public static boolean isDistanceLogRunning(){
        return isDistanceLogRunning;
    }

}
