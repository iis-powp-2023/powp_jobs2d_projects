package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadSecretCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(ShapesFactory.topSecretCommand());
    }
}