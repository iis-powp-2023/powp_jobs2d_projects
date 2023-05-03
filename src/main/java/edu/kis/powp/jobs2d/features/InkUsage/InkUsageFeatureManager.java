package edu.kis.powp.jobs2d.features.InkUsage;

public final class InkUsageFeatureManager extends InkUsageFeature {
    private static InkUsageFeature inkUsageFeature = null;

    public static InkUsageFeature getInkUsageFeature() {
        if (inkUsageFeature == null) {
            inkUsageFeature = new InkUsageFeature();
        }
        return inkUsageFeature;
    }
}
