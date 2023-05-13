package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.*;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONArray;

public class CommandFromJson extends CommandFactory
{
    CommandFromJson()
    {

    }

    @Override
    public boolean acceptText(String input)
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
