package edu.kis.powp.jobs2d.command.recorder;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandRecorder {
    private List<DriverCommand> driverCommands = new ArrayList<>();
    private List<DriverCommand> transformCommands = new ArrayList<>();


    public void addCommand(DriverCommand command){
        driverCommands.add(command);
    }

    public void clearCommand(){
        this.driverCommands.clear();
    }

    public List<DriverCommand> getCommands(){
        return driverCommands;
    }

    public void addTransformCommand(DriverCommand command){
        transformCommands.add(command);
    }

    public void clearTransformCommand(){
        this.transformCommands.clear();
    }

    public List<DriverCommand> getTransformCommands(){
        return transformCommands;
    }

}
