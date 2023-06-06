package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleScaleListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private Job2dDriver doubleScaleDriver;

    private boolean checked;

    public DoubleScaleListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            composite.removeDriver(doubleScaleDriver);
            doubleScaleDriver = null;
            this.checked = false;
        }
        else{
            this.doubleScaleDriver = new TransformationDriver(driverManager.getCurrentDriver(), TransformationFactory.getDoubleScale());
            composite.addDriver(doubleScaleDriver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);
    }
}
