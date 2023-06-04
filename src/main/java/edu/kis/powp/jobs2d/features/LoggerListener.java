package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    public LoggerListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Job2dDriver loggerDriver = new PositionLoggingDriver();
        composite.addDriver(loggerDriver);
        driverManager.setCurrentDriver(composite);

    }
}
