package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.usage.DeviceUsageManager;
import edu.kis.powp.jobs2d.drivers.usage.ServiceThresholds;

import java.util.logging.Logger;

public class DistanceCountingDriver extends DriverDecorator {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final DeviceUsageManager deviceUsageManager = new DeviceUsageManager();

    public DeviceUsageManager getDeviceUsageManager() {
        return this.deviceUsageManager;
    }

    public DistanceCountingDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        if(deviceUsageManager.getHeadDistance() + deviceUsageManager.calculateDistance(x, y) <= ServiceThresholds.HEAD_REPLACE_THRESHOLD.getValue()) {
            super.setPosition(x, y);
            deviceUsageManager.calculateMovingDistance(x, y);
        } else {
            logger.info("Head needs to be serviced before performing this operation.");
        }
    }

    @Override
    public void operateTo(int x, int y) {

        double distance = deviceUsageManager.calculateDistance(x, y);
        if(deviceUsageManager.getOperatingDistance() + distance <= ServiceThresholds.REFILL_THRESHOLD.getValue()) {
            if(deviceUsageManager.getHeadDistance() + distance <= ServiceThresholds.HEAD_REPLACE_THRESHOLD.getValue()) {
                super.operateTo(x,y);
                deviceUsageManager.calculateOperatingDistance(x,y);
            }
            else {
                logger.info("Head needs to be serviced before performing this operation.");
            }
        } else {
            logger.info("Ink needs to be refilled before performing this operation.");
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
