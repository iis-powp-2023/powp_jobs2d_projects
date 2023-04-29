package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;

public class RecordFeature {

    private static Application app;

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupRecorderPlugin(Application application) {
        app = application;
        app.addComponentMenu(edu.kis.powp.jobs2d.features.RecordFeature.class, "Recorder");
    }
}


        /*
        public static void addDriver(String name, Job2dDriver driver) {
            SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
            app.addComponentMenuElement(edu.kis.powp.jobs2d.features.DriverFeature.class, name, listener);
        }

        /**
         * Update driver info.
         */
        /*
        public static void updateDriverInfo() {
            app.updateInfo(driverManager.getCurrentDriver().toString());
        }
        */

