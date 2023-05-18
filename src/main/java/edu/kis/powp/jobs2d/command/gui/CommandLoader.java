package edu.kis.powp.jobs2d.command.gui;

import java.awt.event.ActionEvent;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;

import java.awt.event.*;

public class CommandLoader implements ActionListener
{
    private DriverCommand driverCommand;
    private CommandManager commandManager;

    public CommandLoader(DriverCommand driverCommand, CommandManager commandManager)
    {
        this.driverCommand = driverCommand;
        this.commandManager = commandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        commandManager.setCurrentCommand(driverCommand);
    }
    
}
