package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;

public class Bookmarks {
    private CommandManager commandManager = null;

    private static BookmarksWindow bookmarksWindow = null;
    private static Bookmarks instance = null;
    private String defaultTextInputMessage = "Write here to add description to a bookmark";

    public static Bookmarks getInstance()
    {
        if (instance == null)
        {
            instance = new Bookmarks();
            bookmarksWindow = new BookmarksWindow();
        }

        return instance;
    }

    private Bookmarks() {}

    public BookmarksWindow getBookmarksWindow()
    {
        return bookmarksWindow;
    }

    public void setCommandManager(CommandManager commandManager)
    {
        this.commandManager = commandManager;
    }

    public void bookmarkCurrentCommand()
    {
        try
        {
            DriverCommand command = commandManager.getCurrentCommand();
            CommandLoaderListener action = new CommandLoaderListener(command, commandManager);
            String description = bookmarksWindow.getDescription();
            if (description.equals(defaultTextInputMessage)|| description.equals(""))
            {
                description = "No description";
            }
            bookmarksWindow.addBookmark(command.toString(), description, action);
        }
        catch(Exception e)
        {
            bookmarksWindow.displayFailiureStatus();
        }
        


    }
    
}
