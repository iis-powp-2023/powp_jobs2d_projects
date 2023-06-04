package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.usage.UsageManagerWindow;

public class UsageFeature {
    private static UsageManagerWindow usageManagerWindow;

    public static UsageManagerWindow getUsageManagerWindow(){
        return usageManagerWindow;
    }

    public static void setUsageManagerWindow(UsageManagerWindow usageManagerWindow){
        UsageFeature.usageManagerWindow = usageManagerWindow;
    }
}
