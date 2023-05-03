package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONArray;

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

    private static List<DriverCommand> processText(Scanner scanner)
    {
        scanner.nextLine();
        List<DriverCommand> ret = new ArrayList<DriverCommand>();
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
                return null;
            }
            ret.add(next);
        }
        scanner.close();

        if (hasCommand)
        {
            return ret;
        }
        else
        {
            return null;
        }
        
    }

    public static List<DriverCommand> fromText(String text)
    {
        Scanner scanner = new Scanner(text);
        return processText(scanner);        
    }

    public static List<DriverCommand> fromTextfile(String text) throws FileNotFoundException
    {
        File fileObject = new File(text);
        Scanner scanner = new Scanner(fileObject);
        return processText(scanner);
    }

    public static List<DriverCommand> fromJsonFile(JSONObject input) throws Exception
    {
        JSONArray array = (JSONArray) input.get("actions");
        List<DriverCommand> ret = new ArrayList<DriverCommand>();
        
        for (Object one : array)
        {
            JSONObject action = (JSONObject) one;

            DriverCommand next = createCommand((String) action.get("type"), (Integer) action.get("x"), (Integer) action.get("y"));
            if (next == null)
            {
                return null;
            }
            ret.add(next);            
        }

        return ret;
    }


}