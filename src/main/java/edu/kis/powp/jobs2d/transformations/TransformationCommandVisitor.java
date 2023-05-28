package edu.kis.powp.jobs2d.transformations;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransformationCommandVisitor implements ICommandVisitor {

    protected DriverCommand command;
    private Transformation transformation;

    public TransformationCommandVisitor(DriverCommand command, Transformation transformation) {
        this.command = command;
        this.transformation = transformation;
    }

    @Override
    public void visit(ICompoundCommand commands) {
        List<DriverCommand> modifiedCommands = new ArrayList<>();
        Iterator<DriverCommand> iterator = commands.iterator();
        while(iterator.hasNext()) {
            DriverCommand command = iterator.next();
            command.accept(this);
            command = this.getResult();
            modifiedCommands.add(command);
        }
        this.command = new ImmutableCompoundCommand(modifiedCommands);
    }

    @Override
    public void visit(OperateToCommand command) {
        transformation.calculateNewX(command.getPosX(), command.getPosX());
        this.command = command;
    }

    @Override
    public void visit(SetPositionCommand command) {
        transformation.calculateNewX(command.getPosX(), command.getPosX());
        this.command = command;
    }

    public DriverCommand getResult() {
        return this.command;
    }
}

