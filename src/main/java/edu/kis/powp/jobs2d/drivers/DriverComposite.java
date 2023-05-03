package edu.kis.powp.jobs2d.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.InkUsage.InkUsageFeature;
import edu.kis.powp.jobs2d.features.InkUsage.InkUsageFeatureManager;

public class DriverComposite implements Job2dDriver {

    private final List<Job2dDriver> drivers;
    private final InkUsageFeature inkUsageFeature;
    public DriverComposite() {
        drivers = new ArrayList<>();
        inkUsageFeature = InkUsageFeatureManager.getInkUsageFeature();
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
            inkUsageFeature.saveCoordinates(x, y);
            driver.setPosition(x, y);
        }
    }
    @Override
    public void operateTo(int x, int y) {
        for(Job2dDriver driver : drivers) {
            if (!inkUsageFeature.checkTonerLevel(x, y)) {
                continue;
            }
            driver.operateTo(x, y);
        }
    }

}
