package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.command.manager.LoggerHeadUsageObserver;

public class CommandsFeature {

    private static CommandManager commandManager;

    public static void setupCommandManager() {
        commandManager = new CommandManager();

        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
        LoggerHeadUsageObserver loggerObserver2 = new LoggerHeadUsageObserver();
        commandManager.getChangePublisher().addSubscriber(loggerObserver);
        commandManager.getChangePublisher().addSubscriber(loggerObserver2);

    }

    /**
     * Get manager of application driver command.
     * 
     * @return plotterCommandManager.
     */
    public static CommandManager getDriverCommandManager() {
        return commandManager;
    }
}
