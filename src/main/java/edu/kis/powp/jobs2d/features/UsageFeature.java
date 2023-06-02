package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.usage.UsageManager;

public class UsageFeature {

    private static UsageManager usageManager;

    public static void setupDeviceUsageManager(UsageManager dum) {
        usageManager = dum;
    }

    public static UsageManager getDriverDeviceUsageManager() {
        return usageManager;
    }
}
