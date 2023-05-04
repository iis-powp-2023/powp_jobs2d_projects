package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.*;


public class CompoundCommand implements ICompoundCommand {
    public List<DriverCommand> commands;

    public CompoundCommand(List<DriverCommand> commands) {
        this.commands = commands;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand command : commands) {
            command.execute(driver);
        }
    }

    public ICompoundCommand deepCopy() {
        return new CompoundCommand(new ArrayList<>(commands));
    }

}
