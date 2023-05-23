package edu.kis.powp.jobs2d.command.transformation;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class TransformationCommandVisitor implements ICommandVisitor {

    protected DriverCommand command;
    
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

