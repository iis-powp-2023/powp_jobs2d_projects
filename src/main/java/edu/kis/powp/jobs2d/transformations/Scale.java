package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public class Scale implements Transformation, DriverCommand {

    private final double xCoefficient;
    private final double yCoefficient;

    public Scale(double xCoefficient, double yCoefficient) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
    }

    @Override
    public int calculateNewX(int x, int y) {
        return (int)(xCoefficient * x);
    }

    @Override
    public int calculateNewY(int x, int y) {
        return (int)(yCoefficient * y);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition((int)xCoefficient, (int)yCoefficient);
    }

    @Override
    public void accept(ICommandVisitor visitor){
        visitor.visit((ICompoundCommand) this);
    }
}
