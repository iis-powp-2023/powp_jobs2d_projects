package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceLogListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    public DistanceLogListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DeviceUsageManager deviceUsageManager;

        DistanceCountingDriver driver = new DistanceCountingDriver(driverManager.getCurrentDriver());
        deviceUsageManager = driver.getDeviceUsageManager();
        deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
        composite.addDriver(driver);
        driverManager.setCurrentDriver(composite);

    }
}



