package edu.kis.powp.jobs2d.command.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICommandVisitor;

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
        command.execute(this.executor);
    }
}
