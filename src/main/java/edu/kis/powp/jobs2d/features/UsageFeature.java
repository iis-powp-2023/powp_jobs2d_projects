package edu.kis.powp.jobs2d.features;

public class UsageFeature {

    private static DeviceUsageManager usageManager;

    public static void setupDeviceUsageManager(DeviceUsageManager dum) {
        usageManager = dum;
    }

    public static DeviceUsageManager getDriverDeviceUsageManager() {
        return usageManager;
    }
}
