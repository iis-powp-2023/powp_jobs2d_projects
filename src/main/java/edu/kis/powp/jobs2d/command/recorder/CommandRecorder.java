package edu.kis.powp.jobs2d.command.recorder;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandRecorder {
    private List<DriverCommand> driverCommands = new ArrayList<>();

    public void addCommand(DriverCommand command){
        driverCommands.add(command);
    }
}
