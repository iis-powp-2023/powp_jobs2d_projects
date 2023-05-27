package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

public class CommandFromText extends CommandImporter
{
    CommandFromText()
    {

    }
  
    /**
     * This function accepts a string input formated like the example .txt file in resources, parses it into a name and a list of 
     * DriverCommand objects and seves them in a the object
     * 
     * @param input A string input that contains the name of the shape and a list of commands in the format "command x y" where
     * command is a string representing an action derived by createCommand in CommandImporter, and x and y are integers representing the coordinates.
     * @return The method is returning a boolean value - true if at least one command could be constructed from the input, false if not.
     */
    @Override
    public boolean parseText(String input)
    {
        try
        {
            input += "\n";
            Scanner scanner = new Scanner(input);
            name = scanner.nextLine();
            command = new ArrayList<DriverCommand>();
            boolean hasCommand = false;
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                if (line.equals(""))
                {
                    break;
                }

                hasCommand = true;
                String[] elements = line.split(" ");
                DriverCommand next = createCommand(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                if (next == null)
                {
                    scanner.close();
                    return false;
                }
                command.add(next);
            }
            scanner.close();

            if (hasCommand)
            {
                return true;
            }
            else
            {
                return false;
            }
                
     
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
}
