package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListenerUsageManager;
import edu.kis.powp.jobs2d.events.DriverLabelSubscriber;

public class DriverFeature {

    private static final DriverManager driverManager = new DriverManager();
    private static Application app;



    public static DriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     * 
     * @param application Application context.
     */
    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(DriverFeature.class, "Drivers");
        driverManager.addSubscriber(new DriverLabelSubscriber());
    }

    /**
     * Add driver to context, create button in driver menu.
     * 
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */
    public static void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    public static void addUsageDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListenerUsageManager usageListener = new SelectDriverMenuOptionListenerUsageManager(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, usageListener);
    }

    /**
     * Update driver info.
     */
    public static void updateDriverInfo() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }
}
