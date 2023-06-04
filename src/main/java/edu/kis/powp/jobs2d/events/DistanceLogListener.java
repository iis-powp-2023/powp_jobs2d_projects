package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.features.DeviceUsageManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceLogListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private final DistanceCountingDriver driver;
    public DistanceLogListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        DeviceUsageManager deviceUsageManager;

        this.driver = new DistanceCountingDriver(driverManager.getCurrentDriver());
        deviceUsageManager = driver.getDeviceUsageManager();
        deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(composite.containsDriver(driver)){
            composite.removeDriver(driver);
        }
        else{
            composite.addDriver(driver);
        }
        driverManager.setCurrentDriver(composite);

    }
}



