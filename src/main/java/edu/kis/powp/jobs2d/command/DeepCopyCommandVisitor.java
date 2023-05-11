package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

public class DeepCopyCommandVisitor implements ICommandVisitor {
    private DriverCommand deepCopiedCommand;
    private List<DriverCommand> deepCopiedCompoundCommand;
    @Override
    public void visit(ICompoundCommand command) {
        Iterator<DriverCommand> iterator = command.iterator();

        while(iterator.hasNext()) {
            iterator.next().accept(this);
            this.deepCopiedCompoundCommand.add(this.deepCopiedCommand);
        }
    }

    @Override
    public void visit(OperateToCommand command) {
        try {
            this.deepCopiedCommand = (DriverCommand) command.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void visit(SetPositionCommand command) {
        try {
            this.deepCopiedCommand = (DriverCommand) command.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DriverCommand getDeepCopiedCommand() {
        return deepCopiedCommand;
    }

    public List<DriverCommand> getDeepCopiedCompoundCommand() {
        return deepCopiedCompoundCommand;
    }
}
