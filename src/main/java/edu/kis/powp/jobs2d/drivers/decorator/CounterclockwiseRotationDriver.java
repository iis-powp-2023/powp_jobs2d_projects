package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;

public class CounterclockwiseRotationDriver extends DriverDecorator  {

    public CounterclockwiseRotationDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(y, -x);
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(y, -x);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
