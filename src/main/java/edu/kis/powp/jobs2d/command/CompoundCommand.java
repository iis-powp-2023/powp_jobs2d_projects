package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.*;

public class CompoundCommand implements ICompoundCommand {

    private final List<DriverCommand> commands;

    public CompoundCommand(List<DriverCommand> subCommands) {
        this.commands = subCommands;
    }

    public void execute(Job2dDriver driver) {
        for (DriverCommand command : commands)
            command.execute(driver);
    }

    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public ICompoundCommand createDeepCopy() {
        List<DriverCommand> copySubCommands = new ArrayList<>();

        for (DriverCommand subCommand : commands) {
            copySubCommands.add(subCommand.createDeepCopy());
        }

        return new CompoundCommand(copySubCommands);
    }

}
