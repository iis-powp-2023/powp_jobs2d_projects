package edu.kis.powp.jobs2d.drivers.observer;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverManagerChangeObserverDriverFeatureChange implements Subscriber {
    @Override
    public void update() {
        DriverFeature.updateDriverInfo();
    }
}
