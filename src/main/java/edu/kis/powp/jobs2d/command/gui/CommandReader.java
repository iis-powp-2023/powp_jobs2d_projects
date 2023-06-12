package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;

public interface CommandReader {
    ImmutableCompoundCommand parseCommand(String input, String commandName);
}
