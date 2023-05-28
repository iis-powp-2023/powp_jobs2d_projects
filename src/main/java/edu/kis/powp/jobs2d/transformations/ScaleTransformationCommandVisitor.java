package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class ScaleTransformationCommandVisitor extends TransformationCommandVisitor {

    public ScaleTransformationCommandVisitor(DriverCommand command, Transformation transformation) {
        super(command, transformation);
    }

    @Override
    public void visit(SetPositionCommand command) {
        this.command = new SetPositionCommand(command.getPosX(), command.getPosY());
    }

    @Override
    public void visit(OperateToCommand command) {
        this.command = new OperateToCommand(command.getPosX(), command.getPosY());
    }
}