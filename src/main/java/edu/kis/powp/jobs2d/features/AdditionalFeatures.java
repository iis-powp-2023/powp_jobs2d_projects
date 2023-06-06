package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.util.ArrayList;
import java.util.List;

public class AdditionalFeatures {
    private static Application app;
    public static DriverComposite driverComposite = new DriverComposite();

    public static Job2dDriver mainDriver=null;

    public static Job2dDriver currentDriver=null;
    public static List<String> activeTransformation= new ArrayList<>();
    public static void setupAdditionalFeaturesPlugin(Application application) {

        app = application;
        app.addComponentMenu(AdditionalFeatures.class, "Additional features");
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Logger", new LoggerListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Distance log", new DistanceLogListener(DriverFeature.getDriverManager(),driverComposite),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Vertical flip",new DecorateListener(DriverFeature.getDriverManager(),driverComposite,"VerticalFlip"),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Horizontal flip", new  DecorateListener(DriverFeature.getDriverManager(),driverComposite,"HorizontalFlip"),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Half scale", new DecorateListener(DriverFeature.getDriverManager(),driverComposite,"HalfScale"),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Double scale", new DecorateListener(DriverFeature.getDriverManager(),driverComposite,"DoubleScale"),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Clockwise rotation", new DecorateListener(DriverFeature.getDriverManager(),driverComposite,"ClockwiseRotation"),false);
        app.addComponentMenuElementWithCheckBox(AdditionalFeatures.class, "Counterclockwise rotation", new DecorateListener(DriverFeature.getDriverManager(),driverComposite,"CounterclockwiseRotation"),false);
    }
    public static void removeTransformation(String transformationName){
        System.out.println("Removing transformation: "+transformationName);
        activeTransformation.remove(transformationName);
        driverComposite.removeDriver(AdditionalFeatures.currentDriver);
        currentDriver = mainDriver;
        for (String s : activeTransformation) {
            switch (s) {
                case "HalfScale":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getHalfScale());
                    break;
                case "DoubleScale":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getDoubleScale());
                    break;
                case "VerticalFlip":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getVerticalFlip());
                    break;
                case "HorizontalFlip":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getHorizontalFlip());
                    break;
                case "ClockwiseRotation":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getClockwiseRotation());
                    break;
                case "CounterclockwiseRotation":
                    currentDriver = new TransformationDriver(currentDriver, TransformationFactory.getCounterclockwiseRotation());
                    break;
            }
        }
        driverComposite.addDriver(currentDriver);
        DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
    }
}
