package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.RecordingDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class RecordingDriverDecoratingSubscriber implements Subscriber {

    @Override
    public void update() {
        DriverManager driverManager =  DriverFeature.getDriverManager();
        Job2dDriver currentDriver = driverManager.getCurrentDriver();
        driverManager.setCurrentDriver(new RecordingDriver(currentDriver));
    }
}
