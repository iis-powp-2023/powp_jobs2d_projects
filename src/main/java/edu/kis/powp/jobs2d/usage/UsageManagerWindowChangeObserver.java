package edu.kis.powp.jobs2d.usage;

import edu.kis.powp.jobs2d.features.UsageFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageManagerWindowChangeObserver implements Subscriber {
    private final UsageManager usageManager;
    private UsageManagerWindow usageManagerWindow = null;

    public UsageManagerWindowChangeObserver(UsageManager usageManager) {
        super();
        this.usageManager = usageManager;
    }

    public String toString() {
        return "Current usage change observer for usage manager window";
    }

    @Override
    public void update() {
        if(this.usageManagerWindow == null) this.usageManagerWindow = UsageFeature.getUsageManagerWindow();
        this.usageManagerWindow.updateHeadDistanceField(Double.toString(Math.round(usageManager.getHeadDistance())));
        this.usageManagerWindow.updateOperatingDistanceField(Double.toString(Math.round(usageManager.getOperatingDistance())));
        this.usageManagerWindow.setCurrentUsageManager(usageManager);
        this.usageManagerWindow.updateUsageBar(usageManager.getDeviceUsage());
    }

}
