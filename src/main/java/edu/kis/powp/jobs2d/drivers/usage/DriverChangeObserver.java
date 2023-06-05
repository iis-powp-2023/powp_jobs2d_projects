package edu.kis.powp.jobs2d.drivers.usage;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    private final DriverUsageWindow usageWindow;
    public String toString() {
        return "Driver State Observer";
    }

    public DriverChangeObserver(DriverUsageWindow usageWindow) {
        this.usageWindow = usageWindow;
    }

    @Override
    public void update() {
        usageWindow.setCurrentDriver(DriverFeature.getDriverManager().getCurrentDriver());
    }
}
