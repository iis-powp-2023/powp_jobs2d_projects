package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.features.UsageFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageManagerWindowChangeObserver implements Subscriber {

    private UsageManagerWindow usageManagerWindow;

    public UsageManagerWindowChangeObserver(UsageManagerWindow usageManagerWindow) {
        super();
        this.usageManagerWindow = usageManagerWindow;
    }

    public String toString() {
        return "---";
    }

    @Override
    public void update() {
        usageManagerWindow.updateHeadDistanceField(Double.toString(Math.round(UsageFeature.getDriverDeviceUsageManager().getHeadDistance())));
        usageManagerWindow.updateOperatingDistanceField(Double.toString(Math.round(UsageFeature.getDriverDeviceUsageManager().getOperatingDistance())));
    }

}
