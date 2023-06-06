package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.features.DeviceUsageManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceLogListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private DistanceCountingDriver distanceCountingDriver =null;

    private boolean checked;
    public DistanceLogListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            distanceCountingDriver.getDeviceUsageManager().getDistanceChangePublisher().clearObservers();
            composite.removeDriver(distanceCountingDriver);
            distanceCountingDriver = null;
            this.checked = false;
        }
        else{
            DeviceUsageManager deviceUsageManager;
            DistanceCountingDriver driver = new DistanceCountingDriver(AdditionalFeatures.mainDriver);
            deviceUsageManager = driver.getDeviceUsageManager();
            deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
            this.distanceCountingDriver = driver;
            composite.addDriver(driver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);

    }
}



