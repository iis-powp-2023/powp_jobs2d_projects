package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

public class CommandFromText extends CommandFactory
{

    CommandFromText()
    {

    }
  
    @Override
    public boolean acceptText(String input)
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
