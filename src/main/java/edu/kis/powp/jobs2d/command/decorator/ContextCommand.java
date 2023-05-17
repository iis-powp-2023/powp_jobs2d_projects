package edu.kis.powp.jobs2d.command.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;

public class ContextCommand implements DriverCommand {
    private final DriverCommand command;
    private final Job2dDriver executor;

    public ContextCommand(DriverCommand command, Job2dDriver executor) {
        this.command = command;
        this.executor = executor;
    }

    @Override
    public void execute(Job2dDriver driver) {
        if(driver == null)
            command.execute(this.executor);
        else
            command.execute(driver);
    }

    @Override
    public void accept(ICommandVisitor visitor) {
        if(command instanceof ICompoundCommand)
            visitor.visit((ICompoundCommand) command);
        if(command instanceof SetPositionCommand)
            visitor.visit((SetPositionCommand) command);
        if(command instanceof OperateToCommand)
            visitor.visit((OperateToCommand) command);
    }
}
