public class CommandFromJSON
{
    List<DriverCommand> command;
    String name;

    CommandFromJSON()
    {

    }

    public boolean acceptText(String input)
    {
        try
                 {

                     JSONObject inputJson = new JSONObject(input);
 		            name = (String) inputJson.get("name");

                     JSONArray array = (JSONArray) input.get("actions");
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
