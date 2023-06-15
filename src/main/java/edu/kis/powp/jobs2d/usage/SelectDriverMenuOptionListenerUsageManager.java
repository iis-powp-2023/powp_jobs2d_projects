package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.UsageFeature;
import edu.kis.powp.jobs2d.usage.UsageManager;
import edu.kis.powp.jobs2d.usage.UsageManagerWindow;

public class SelectDriverMenuOptionListenerUsageManager implements ActionListener {
    private DriverManager driverManager;
    private Job2dDriver driver = null;
    private UsageManagerWindow usageManagerWindow = null;

    public SelectDriverMenuOptionListenerUsageManager(Job2dDriver driver, DriverManager driverManager) {
        this.driverManager = driverManager;
        this.driver = driver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.setCurrentDriver(driver);

        UsageManager usageManager = ((DistanceCountingDriver)driver).getDeviceUsageManager();

        UsageFeature.getUsageManagerWindow().setCurrentUsageManager(usageManager);
        if(this.usageManagerWindow == null) this.usageManagerWindow = UsageFeature.getUsageManagerWindow();
        this.usageManagerWindow.updateHeadDistanceField(Double.toString(Math.round(usageManager.getHeadDistance())));
        this.usageManagerWindow.updateOperatingDistanceField(Double.toString(Math.round(usageManager.getOperatingDistance())));
        this.usageManagerWindow.setCurrentUsageManager(usageManager);
        this.usageManagerWindow.updateUsageWindow(usageManager.getDeviceUsage());
    }
}
