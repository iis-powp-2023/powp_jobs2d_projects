package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.features.AdditionalFeatures;
import edu.kis.powp.jobs2d.features.DeviceUsageManager;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceLogListener implements ActionListener {
    private DriverManager driverManager;
    private DriverComposite composite = null;

    private DistanceCountingDriver driver=null;

    private boolean checked;
    public DistanceLogListener(DriverManager driverManager, DriverComposite composite) {
        this.driverManager = driverManager;
        this.composite = composite;
        this.checked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checked){
            driver.getDeviceUsageManager().getDistanceChangePublisher().clearObservers();
            composite.removeDriver(driver);
            this.checked = false;
        }
        else{
            DeviceUsageManager deviceUsageManager;
            DistanceCountingDriver driver = new DistanceCountingDriver(AdditionalFeatures.mainDriver);
            deviceUsageManager = driver.getDeviceUsageManager();
            deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
            this.driver = driver;
            composite.addDriver(driver);
            this.checked = true;
        }
        driverManager.setCurrentDriver(composite);

    }
}



