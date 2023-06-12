package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public class CommandReaderManager {
    private List<DriverCommand> command;
    private String name;

    public CommandReaderManager(List<DriverCommand> command, String name) {
        this.command = command;
        this.name = name;
    }

    public List<DriverCommand> getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
