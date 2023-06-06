package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ICommandVisitor;

public interface Transformation {
    int calculateNewX(int x, int y);
    int calculateNewY(int x, int y);
    public void execute(Job2dDriver driver);
}
