package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.gui.HistoryOfUsedCommandsWindow;
import edu.kis.powp.observer.Subscriber;

public class HistoryOfUsedCommandsSubscriber implements Subscriber {
    HistoryOfUsedCommandsWindow historyOfUsedCommandsWindow;
    public HistoryOfUsedCommandsSubscriber(HistoryOfUsedCommandsWindow historyOfUsedCommandsWindow){
        this.historyOfUsedCommandsWindow = historyOfUsedCommandsWindow;
    }

    @Override
    public void update() {

        HistoryOfUsedCommandsManager.addCommand();
        historyOfUsedCommandsWindow.updateHistoryOfUsedCommandsField();

    }
}
