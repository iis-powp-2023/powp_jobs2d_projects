package edu.kis.powp.jobs2d.features;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorizontalFlipListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    public HorizontalFlipListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Job2dDriver horizontalFlipDriver = new TransformationDriver(driverManager.getCurrentDriver(), TransformationFactory.getHorizontalFlip());
        composite.addDriver(horizontalFlipDriver);
        driverManager.setCurrentDriver(composite);
    }
}
