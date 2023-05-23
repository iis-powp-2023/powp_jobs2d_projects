package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

public abstract class CommandImporter 
{
    protected List<DriverCommand> command = null;
    protected String name = "";

    public List<DriverCommand> getCommand()
    {
        return command;
    }
    
    public String getName()
    {
        return name;
    }

    abstract public boolean parseText (String input);

    protected static DriverCommand createCommand(String commandIdentifier, Integer x, Integer y) throws NoSuchCommandIdentifierException
    {
        switch(commandIdentifier)
        {
            case "OT":
                return new OperateToCommand(x, y);

            case "SP":
                return new SetPositionCommand(x, y);

            default:
                throw new NoSuchCommandIdentifierException();
        }

    }
}
