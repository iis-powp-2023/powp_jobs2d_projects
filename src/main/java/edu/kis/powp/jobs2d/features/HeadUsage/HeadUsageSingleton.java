package edu.kis.powp.jobs2d.features.HeadUsage;

public final class HeadUsageSingleton {
    private static HeadUsageFeature headUsageFeature = null;

    private static void initHeadUsageFeature() {
        if (headUsageFeature == null) {
            headUsageFeature = new HeadUsageFeature();
        }
    }

    public static HeadUsageFeature setupHeadUsageFeature(double amount) {
        initHeadUsageFeature();
        headUsageFeature.fillPlotterWithInk(amount);
        return headUsageFeature;
    }
}
