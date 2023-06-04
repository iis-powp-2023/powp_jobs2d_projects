package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.*;

public class AdditionalFeatures {
    private static Application app;

    public static void setupAdditionalFeaturesPlugin(Application application) {
        SelectStartRecordingOptionListener selectStartRecordingOptionListener = new SelectStartRecordingOptionListener();
        SelectStopRecordingOptionListener selectStopRecordingOptionListener = new SelectStopRecordingOptionListener();
        SelectClearRecordingOptionListener selectClearRecordingOptionListener = new SelectClearRecordingOptionListener();


        app = application;
        app.addComponentMenu(AdditionalFeatures.class, "Additional features");
        app.addComponentMenuElementWithCheckBox(edu.kis.powp.jobs2d.features.RecordFeature.class, "distance log", selectStartRecordingOptionListener,false);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Lines", null);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Flip", null);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Scale", null);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Rotation", null);
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
                DriverFeature.getDriverManager());

        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
        DriverFeature.getDriverManager().addSubscriber(new RecordingDriverDecoratingSubscriber());
    }
}
