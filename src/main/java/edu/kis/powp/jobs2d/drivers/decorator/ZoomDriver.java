package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.MouseAdjustmentFeature;

public class ZoomDriver extends DriverDecorator {

    private final MouseAdjustmentFeature mouseAdjustmentFeature;

    public ZoomDriver(Job2dDriver driver, MouseAdjustmentFeature mouseAdjustmentFeature) {
        super(driver);
        this.mouseAdjustmentFeature = mouseAdjustmentFeature;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x*2,y*2);
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(x*2,y*2);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
