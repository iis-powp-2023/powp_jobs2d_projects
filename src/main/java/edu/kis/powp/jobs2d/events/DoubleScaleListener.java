package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleScaleListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private final Job2dDriver doubleScaleDriver;

    public DoubleScaleListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.doubleScaleDriver = new TransformationDriver(driverManager.getCurrentDriver(), TransformationFactory.getDoubleScale());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(composite.containsDriver(doubleScaleDriver)){
            composite.removeDriver(doubleScaleDriver);
        }
        else{
            composite.addDriver(doubleScaleDriver);
        }
        driverManager.setCurrentDriver(composite);
    }
}
