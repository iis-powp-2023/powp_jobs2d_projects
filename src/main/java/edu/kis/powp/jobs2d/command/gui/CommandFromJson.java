package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONArray;

public class CommandFromJson extends CommandImporter
{
    CommandFromJson()
    {

    }

    /**
     * This function accepts a string input formated like the example .json file in resources, parses it into a name and a list of 
     * DriverCommand objects and seves them in the object
     * 
     * @param input A string input that should be in JSON format and contain a "name" field and an
     * "actions" field, where "actions" is an array of objects with "type", "x", and "y" fields.
     * @return The method returns a boolean value - true if at least one command could be constructed from the input, false if not.
     */
    @Override
    public boolean parseText(String input)
    {
        try
        {
            JSONObject inputJson = new JSONObject(input);
            name = (String) inputJson.get("name");

            JSONArray array = (JSONArray) inputJson.get("actions");
            command = new ArrayList<DriverCommand>();
            
            for (Object one : array)
            {
                JSONObject action = (JSONObject) one;

                DriverCommand next = createCommand((String) action.get("type"), (Integer) action.get("x"), (Integer) action.get("y"));
                if (next == null)
                {
                    return false;
                }
                command.add(next);            
            }

            return true;

        }
        catch (Exception ex)
        {

            return false;
        }
    }
    
}
