package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;

public class HorizontalFlipDriver extends DriverDecorator {
    public HorizontalFlipDriver(Job2dDriver driver) {
        super(driver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(-x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(-x,y);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
