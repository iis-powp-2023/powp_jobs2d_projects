package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FigureCommandVisitor implements ICommandVisitor {

    protected DriverCommand command;

    @Override
    public void visit(OperateToCommand command) {
        this.command = command;
    }

    @Override
    public void visit(SetPositionCommand command) {
        this.command = command;
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

    public DriverCommand getResult() {
        return this.command;
    }
}

