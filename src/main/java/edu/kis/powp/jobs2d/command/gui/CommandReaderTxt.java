package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private List<String> readFromFileTxt(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Stream<String> stringStream = br.lines();
            return stringStream
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ICompoundCommand readCommandFromFile(String filePath) {
        List<DriverCommand> tempList = new ArrayList<>();
//        ImmutableCompoundCommand compoundCommand = new ImmutableCompoundCommand(tempList);
//        List<String> commandsList = readFromFileTxt(filePath);
//        for (String s : commandsList) {
//            compoundCommand = (ImmutableCompoundCommand) compoundCommand.addCommandToExistingCommandsList(parseCommandLine(s));
//        }
//        return compoundCommand;
        return null;
    }
}
