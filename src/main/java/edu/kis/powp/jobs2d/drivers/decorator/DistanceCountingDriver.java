package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.usage.UsageManager;

public class DistanceCountingDriver extends DriverDecorator {
    private final UsageManager usageManager = new UsageManager();

    public UsageManager getDeviceUsageManager() {
        return this.usageManager;
    }

    public DistanceCountingDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x,y);
        usageManager.calculateMovingDistance(x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        usageManager.calculateOperatingDistance(x,y);
        if(usageManager.canOperate()) super.operateTo(x,y);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
