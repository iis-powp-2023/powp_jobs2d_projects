package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerticalFlipListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private Job2dDriver verticalFlipDriver;

    private boolean checked;

    public VerticalFlipListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            composite.removeDriver(verticalFlipDriver);
            verticalFlipDriver = null;
            this.checked = false;
        }
        else{
            this.verticalFlipDriver = new TransformationDriver(AdditionalFeatures.mainDriver, TransformationFactory.getVerticalFlip());
            composite.addDriver(verticalFlipDriver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);
    }
}
