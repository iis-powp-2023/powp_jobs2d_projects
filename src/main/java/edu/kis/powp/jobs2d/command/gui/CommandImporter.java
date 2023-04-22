package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandImporter
{
    private static DriverCommand createCommand(String command, Integer x, Integer y)
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

    public static List<DriverCommand> fromText(String text)
    {
        Scanner scanner = new Scanner(text);
        scanner.nextLine();
        List<DriverCommand> ret = new ArrayList<DriverCommand>();
        while (scanner.hasNextLine()) 
        {
            String line = scanner.nextLine();
            if (line.equals(""))
            {
                return ret;
            }
            String[] elements = line.split(" ");
            DriverCommand next = createCommand(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
            if (next == null)
            {
                return null;
            }
            ret.add(next);
        }
        scanner.close();
        return ret;
    }

    public static List<DriverCommand> fromTextfile(String text)
    {
        return null;
    }


}