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
    private Job2dDriver loggerDriver;

    private boolean checked;
    public LoggerListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            composite.removeDriver(loggerDriver);
            this.checked = false;
        }
        else{
            this.loggerDriver = new PositionLoggingDriver();
            composite.addDriver(loggerDriver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);

    }
}
