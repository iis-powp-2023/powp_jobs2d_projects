package edu.kis.powp.jobs2d.command.gui;

public abstract class CommandFactory
{
    public static CommandImporter interpretInput(String input)
    {
        CommandFromText fromText = new CommandFromText();
        if (fromText.parseText(input))
        {
            return fromText;
        }

        CommandFromJson fromJson = new CommandFromJson();
        if (fromJson.parseText(input))
        {
            return fromJson;
        }

        return null;
    }
}
