package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.features.DeviceUsageManager;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.transformations.Transformation;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DecorateListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private Job2dDriver driver=null;
    private String name;

    private boolean checked;
    public DecorateListener(DriverManager driverManager, DriverComposite composite,String name) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
        this.name = name;
    }
    private static void removeTransformation(String transformationName){
        System.out.println("Removing transformation: "+transformationName);
        AdditionalFeatures.activeTransformation.remove(transformationName);
        AdditionalFeatures.driverComposite.removeDriver(AdditionalFeatures.currentDriver);
        AdditionalFeatures.currentDriver =  AdditionalFeatures.mainDriver;
        for (String s :  AdditionalFeatures.activeTransformation) {
            Method method;
            try {
                method = TransformationFactory.class.getMethod("get"+s);
                AdditionalFeatures.currentDriver = new TransformationDriver(AdditionalFeatures.currentDriver, (Transformation) method.invoke(null));

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        AdditionalFeatures.driverComposite.addDriver( AdditionalFeatures.currentDriver);
        DriverFeature.getDriverManager().setCurrentDriver( AdditionalFeatures.driverComposite);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            removeTransformation(name);
            this.checked = false;
        }
        else{
            Method method;
            try {
               method = TransformationFactory.class.getMethod("get"+name);
               driver = new TransformationDriver(AdditionalFeatures.currentDriver, (Transformation) method.invoke(null));

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            AdditionalFeatures.activeTransformation.add(name);
            this.checked = true;
            composite.removeDriver(AdditionalFeatures.currentDriver);
            AdditionalFeatures.currentDriver = this.driver;
            composite.addDriver(AdditionalFeatures.currentDriver);
            driverManager.setCurrentDriver(composite);
        }
    }
}
