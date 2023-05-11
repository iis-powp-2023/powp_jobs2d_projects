package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DeviceUsageManager;

public class DistanceCountingDriver extends DriverDecorator {
    private final DeviceUsageManager deviceUsageManager = new DeviceUsageManager();

    public DistanceCountingDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x,y);
        deviceUsageManager.calculateMovingDistance(x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(x,y);
        deviceUsageManager.calculateOperatingDistance(x,y);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
