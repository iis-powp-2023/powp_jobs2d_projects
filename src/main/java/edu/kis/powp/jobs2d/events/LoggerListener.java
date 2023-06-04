package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;
    private final Job2dDriver loggerDriver;
    public LoggerListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.loggerDriver = new PositionLoggingDriver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(composite.containsDriver(loggerDriver)){
            composite.removeDriver(loggerDriver);
        }
        else{
            composite.addDriver(loggerDriver);
        }
        driverManager.setCurrentDriver(composite);

    }
}
