package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.events.*;

public class AdditionalFeatures {
    private static Application app;
    public static DriverComposite driverComposite = new DriverComposite();

    public static Job2dDriver mainDriver=null;
    public static void setupAdditionalFeaturesPlugin(Application application) {

        app = application;
        app.addComponentMenu(AdditionalFeatures.class, "Additional features");
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Logger", new LoggerListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Distance log", new DistanceLogListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Vertical flip",new VerticalFlipListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Horizontal flip", new HorizontalFlipListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Half scale", new HalfScaleListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Double scale", new DoubleScaleListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Clockwise rotation", new ClockwiseRotationListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Counterclockwise rotation", new CounterclockwiseRotationListener(DriverFeature.getDriverManager(),driverComposite),false);
    }
}
