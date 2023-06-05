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

public class ClockwiseRotationListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private Job2dDriver clockwiseRotationDriver;

    private boolean checked;

    public ClockwiseRotationListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked) {
            composite.removeDriver(clockwiseRotationDriver);
            this.checked = false;
        }
        else{
            this.clockwiseRotationDriver = new TransformationDriver(driverManager.getCurrentDriver(), TransformationFactory.getClockwiseRotation());
            composite.addDriver(clockwiseRotationDriver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);
    }
}
