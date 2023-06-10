package edu.kis.powp.jobs2d.usage;

import edu.kis.powp.jobs2d.features.UsageFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageManagerWindowChangeObserver implements Subscriber {
    private final UsageManager usageManager;

    public UsageManagerWindowChangeObserver(UsageManager usageManager) {
        super();
        this.usageManager = usageManager;
    }

    public String toString() {
        return "Current usage change observer for usage manager window";
    }

    @Override
    public void update() {
        UsageFeature.getUsageManagerWindow().updateHeadDistanceField(Double.toString(Math.round(usageManager.getHeadDistance())));
        UsageFeature.getUsageManagerWindow().updateOperatingDistanceField(Double.toString(Math.round(usageManager.getOperatingDistance())));
        UsageFeature.getUsageManagerWindow().setCurrentUsageManager(usageManager);
        UsageFeature.getUsageManagerWindow().updateUsageBar(usageManager.getDeviceUsage());
    }

}
