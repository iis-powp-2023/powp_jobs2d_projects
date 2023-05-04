package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;


public abstract class DriverDecorator implements Job2dDriver {
    protected Job2dDriver driver;

    public DriverDecorator(Job2dDriver driver){
        this.driver = driver;
    }

    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(x,y);
    }

    @Override
    public String toString() {
        return driver.toString();
    }
}
