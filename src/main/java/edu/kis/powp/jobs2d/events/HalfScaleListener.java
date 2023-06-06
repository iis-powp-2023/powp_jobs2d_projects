package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalfScaleListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private Job2dDriver halfScaleDriver;

    private boolean checked;

    public HalfScaleListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            composite.removeDriver(halfScaleDriver);
            halfScaleDriver = null;
            this.checked = false;
        }
        else{
            this.halfScaleDriver = new TransformationDriver(AdditionalFeatures.mainDriver, TransformationFactory.getHalfScale());
            composite.addDriver(halfScaleDriver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);
    }
}
