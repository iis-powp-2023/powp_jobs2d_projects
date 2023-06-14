package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.transformations.Transformation;

public class TransformationDriver  extends DriverDecorator {

    private final Transformation transformation;
    public TransformationDriver(Job2dDriver driver, Transformation transformation) {
        super(driver);
        this.transformation = transformation;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(this.transformation.calculateNewX(x, y), this.transformation.calculateNewY(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(this.transformation.calculateNewX(x, y), this.transformation.calculateNewY(x, y));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
