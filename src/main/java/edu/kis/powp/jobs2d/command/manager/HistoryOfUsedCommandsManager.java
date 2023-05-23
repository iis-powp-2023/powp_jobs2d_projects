package edu.kis.powp.jobs2d.command.manager;


import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryOfUsedCommandsManager {
    private static List<DriverCommand> historyOfUsedCommands = new ArrayList<>();
    private static List<Date> dateOfUsedCommands = new ArrayList<>();
    private static CommandManager commandManager;



    public static List<DriverCommand> getHistoryOfUsedCommands() {
        return historyOfUsedCommands;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public static List<Date> getDateOfUsedCommands() {
        return dateOfUsedCommands;
    }

    static synchronized void addCommand() {
        historyOfUsedCommands.add(commandManager.getCurrentCommand());
        dateOfUsedCommands.add(new Date());
    }









}
