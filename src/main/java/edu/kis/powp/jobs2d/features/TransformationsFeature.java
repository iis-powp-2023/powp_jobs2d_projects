package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.SelectTransformationListener;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;


public class TransformationsFeature {
    private static Application app;

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupTransformationPlugin(Application application) {
        app = application;
        app.addComponentMenu(TransformationsFeature.class, "Transformation");

        SelectTransformationListener listener = new SelectTransformationListener(TransformationFactory.getVerticalFlip());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Vertical flip", listener);

        listener = new SelectTransformationListener(TransformationFactory.getHorizontalFlip());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Horizontal flip", listener);

        listener = new SelectTransformationListener(TransformationFactory.getHalfScale());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Half scale", listener);

        listener = new SelectTransformationListener(TransformationFactory.getDoubleScale());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Double scale", listener);

        listener = new SelectTransformationListener(TransformationFactory.getClockwiseRotation());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Clockwise rotation", listener);

        listener = new SelectTransformationListener(TransformationFactory.getCounterclockwiseRotation());
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.TransformationsFeature.class, "Counterclockwise", listener);
    }

}



