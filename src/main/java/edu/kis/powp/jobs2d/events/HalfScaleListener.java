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

public class HalfScaleListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private final Job2dDriver halfScaleDriver;

    public HalfScaleListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.halfScaleDriver = new TransformationDriver(driverManager.getCurrentDriver(), TransformationFactory.getHalfScale());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(composite.containsDriver(halfScaleDriver)){
            composite.removeDriver(halfScaleDriver);
        }
        else{
            composite.addDriver(halfScaleDriver);
        }
        driverManager.setCurrentDriver(composite);
    }
}
