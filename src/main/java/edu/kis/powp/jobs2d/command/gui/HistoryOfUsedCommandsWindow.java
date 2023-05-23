package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.HistoryOfUsedCommandsManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class HistoryOfUsedCommandsWindow extends JFrame implements WindowComponent {

    static private Container content;
    static private List<JTextArea> historyOfUsedCommandsFields = new ArrayList<>();

    public HistoryOfUsedCommandsWindow(HistoryOfUsedCommandsManager historyOfUsedCommandsManager) {
        this.setTitle("History of used commands");
        this.setSize(400, 400);
        content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        int index=0;
        for(DriverCommand s: HistoryOfUsedCommandsManager.getHistoryOfUsedCommands()){
            JTextArea historyOfUsedCommandsField = new JTextArea(HistoryOfUsedCommandsManager.getDateOfUsedCommands().get(index)+" "+s.toString());
            index++;
            historyOfUsedCommandsField.setEditable(false);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.gridx = 0;
            c.weighty = 1;

            content.add(historyOfUsedCommandsField,c);

        }



    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }

    }

    static synchronized public void updateHistoryOfUsedCommandsField() {
        historyOfUsedCommandsFields.clear();
        content.removeAll();
        int index=0;

        for(DriverCommand s: HistoryOfUsedCommandsManager.getHistoryOfUsedCommands()){

            JTextArea historyOfUsedCommandsField = new JTextArea(HistoryOfUsedCommandsManager.getDateOfUsedCommands().get(index)+" "+s.toString());
            index++;
            historyOfUsedCommandsField.setEditable(false);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.gridx = 0;
            c.weighty = 1;
            historyOfUsedCommandsField.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    CommandsFeature.getDriverCommandManager().setCurrentCommand(s);
                }
            });

            content.add(historyOfUsedCommandsField,c);
        }
        content.revalidate();
    }
}
