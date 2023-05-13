package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

import org.reflections.*;

public abstract class CommandFactory
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

    public boolean acceptText(String input)
    {
        return false;
    }

    protected static DriverCommand createCommand(String command, Integer x, Integer y)
    {
        switch(command)
        {
            case "OT":
                return new OperateToCommand(x, y);

            case "SP":
                return new SetPositionCommand(x, y);

            default:
                return null;
        }

    }

    public static CommandFactory interpretInput(String input)
    {
        Reflections reflections = new Reflections("edu.kis.powp.jobs2d.command.gui");    
        Set<Class<? extends CommandFactory>> classes = reflections.getSubTypesOf(CommandFactory.class);

        for(Class<? extends CommandFactory> c : classes)
        {
            try
            {
                CommandFactory returningCommand = c.getDeclaredConstructor().newInstance();
                if (returningCommand.acceptText(input))
                {
                    return returningCommand;
                }
            }
            catch (Exception e)
            {

            }
        }

        return null;
    }
}
