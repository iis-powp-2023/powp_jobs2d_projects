package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandReaderTxt implements CommandReader {
    public CommandReaderTxt() {
    }

    private DriverCommand createCommand(String operation, int x, int y) {
        switch (operation) {
            case "operateto":
                return new OperateToCommand(x, y);
            case "setposition":
                return new SetPositionCommand(x, y);
        }
        throw new IllegalArgumentException("Function not found");
    }

    private DriverCommand parseCommandLine(String commandLine) {
        String[] words = commandLine.split("\\s+");

        String command = words[0];
        int x = Integer.parseInt(words[1]);
        int y = Integer.parseInt(words[2]);

        return createCommand(command, x, y);
    }

    @Override
    public ImmutableCompoundCommand parseCommand(String loadedCommand, String commandName) {
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder(commandName);
        List<String> commandList = Arrays.asList(loadedCommand.split("\\n"));
        for (String s : commandList) {
            builder.addCommand(parseCommandLine(s));
        }
        return builder.build();
    }
}
