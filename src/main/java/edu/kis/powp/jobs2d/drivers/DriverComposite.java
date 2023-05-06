package edu.kis.powp.jobs2d.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageFeature;
import edu.kis.powp.jobs2d.features.HeadUsage.HeadUsageSingleton;

public class DriverComposite implements Job2dDriver {

    private final List<Job2dDriver> drivers;
    private final HeadUsageFeature headUsageFeature;
    public DriverComposite() {
        drivers = new ArrayList<>();
        headUsageFeature = HeadUsageSingleton.setupHeadUsageFeature(500.0);
    }
    public void addDriver(Job2dDriver driver) {
        drivers.add(driver);
    }
    public void clearDrivers() {
        drivers.clear();
    }
    @Override
    public void setPosition(int x, int y) {
        for (Job2dDriver driver : drivers) {
            headUsageFeature.saveCoordinates(x, y);
            driver.setPosition(x, y);
        }
    }
    @Override
    public void operateTo(int x, int y) {
        for(Job2dDriver driver : drivers) {
            if (!headUsageFeature.checkTonerLevel(x, y)) {
                continue;
            }
            driver.operateTo(x, y);
        }
    }

}
