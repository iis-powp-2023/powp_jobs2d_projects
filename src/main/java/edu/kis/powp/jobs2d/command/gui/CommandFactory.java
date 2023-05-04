public class CommandFactory
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
}
