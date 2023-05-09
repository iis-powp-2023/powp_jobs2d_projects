package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ImmutableComplexCommand implements ICompoundCommand {
    ArrayList<DriverCommand> commands;

    @Override
    public void execute(Job2dDriver driver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

}
