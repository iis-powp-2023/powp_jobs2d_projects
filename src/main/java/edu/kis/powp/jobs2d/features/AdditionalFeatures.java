package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.*;

public class AdditionalFeatures {
    private static Application app;

    public static void setupAdditionalFeaturesPlugin(Application application) {

        app = application;
        app.addComponentMenu(AdditionalFeatures.class, "Additional features");
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Distance log", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Vertical flip", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Horizontal flip", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Half scale", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Double scale", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Clockwise rotation", null,false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Counterclockwise rotation", null,false);
    }
}
