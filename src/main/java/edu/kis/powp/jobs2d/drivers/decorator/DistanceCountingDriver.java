package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.usage.DeviceUsageManager;
import edu.kis.powp.jobs2d.drivers.usage.ErrorType;
import edu.kis.powp.jobs2d.drivers.usage.ServiceThresholds;
import edu.kis.powp.jobs2d.drivers.usage.UsageErrorStrategy;

public class DistanceCountingDriver extends DriverDecorator {
    private final DeviceUsageManager deviceUsageManager = new DeviceUsageManager();
    private UsageErrorStrategy errorHandlingStrategy;

    public DeviceUsageManager getDeviceUsageManager() {
        return this.deviceUsageManager;
    }

    public DistanceCountingDriver(Job2dDriver driver, UsageErrorStrategy strategy) {
        super(driver);
        this.errorHandlingStrategy = strategy;
    }

    public UsageErrorStrategy getErrorHandlingStrategy() {
        return errorHandlingStrategy;
    }

    public void setErrorHandlingStrategy(UsageErrorStrategy strategy) {
        this.errorHandlingStrategy = strategy;
    }

    @Override
    public void setPosition(int x, int y) {
        if(deviceUsageManager.getHeadDistance() + deviceUsageManager.calculateDistance(x, y) <= ServiceThresholds.HEAD_REPLACE_THRESHOLD.getValue()) {
            super.setPosition(x, y);
            deviceUsageManager.calculateMovingDistance(x, y);
        } else {
            errorHandlingStrategy.execute(ErrorType.HEAD_NEEDS_SERVICE);
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
                errorHandlingStrategy.execute(ErrorType.HEAD_NEEDS_SERVICE);
            }
        } else {
            errorHandlingStrategy.execute(ErrorType.INK_NEEDS_REFILL);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
