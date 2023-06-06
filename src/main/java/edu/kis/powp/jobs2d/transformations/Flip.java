package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public class Flip implements Transformation, DriverCommand {

    private final int xCoefficient;
    private final int yCoefficient;

    public Flip(int xCoeff, int yCoeff) {
        this.xCoefficient = xCoeff;
        this.yCoefficient = yCoeff;
    }

    @Override
    public int calculateNewX(int x, int y) {
        return xCoefficient * x;
    }

    @Override
    public int calculateNewY(int x, int y) {
        return yCoefficient * y;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition(xCoefficient, yCoefficient);
    }

    @Override
    public void accept(ICommandVisitor visitor){
        visitor.visit((ICompoundCommand) this);
    }

}
