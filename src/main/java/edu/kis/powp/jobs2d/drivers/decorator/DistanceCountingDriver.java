package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DistanceFeature;
import edu.kis.powp.jobs2d.features.RecordFeature;

public class DistanceCountingDriver extends DriverDecorator {

    DistanceFeature distanceFeature = new DistanceFeature();

    public DistanceCountingDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x,y);
        distanceFeature.calculateMovingDistance(x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(x,y);
        distanceFeature.calculateOperatingDistance(x,y);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
