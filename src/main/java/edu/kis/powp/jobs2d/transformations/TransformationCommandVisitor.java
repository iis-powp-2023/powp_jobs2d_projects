package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;


public class TransformationCommandVisitor implements ICommandVisitor  {

    protected DriverCommand command;
    private Transformation transformation;

    public TransformationCommandVisitor(DriverCommand command, Transformation transformation) {
        this.command = command;
        this.transformation = transformation;
    }

    @Override
    public void visit(ICompoundCommand commands) {
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder(transformation.toString());
        Iterator<DriverCommand> iterator = commands.iterator();

        while(iterator.hasNext()) {
            DriverCommand command = iterator.next();
            command.accept(this);
            command = this.getResult();
            builder.addCommand(command);
        }

        this.command = builder.build();
    }

    @Override
    public void visit(OperateToCommand command) {
        command.setPosX(transformation.calculateNewX(command.getPosX(), command.getPosX()));
        command.setPosY(transformation.calculateNewY(command.getPosY(), command.getPosY()));
        this.command = command;
    }

    @Override
    public void visit(SetPositionCommand command) {
        command.setPosX(transformation.calculateNewX(command.getPosX(), command.getPosX()));
        command.setPosY(transformation.calculateNewY(command.getPosY(), command.getPosY()));
        this.command = command;
    }

    public DriverCommand getResult() {
        return this.command;
    }

}
