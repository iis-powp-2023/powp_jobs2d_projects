package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverFeatureSubscriber implements Subscriber {

    @Override
    public void update() {
        DriverFeature.updateDriverInfo();

    }
}
