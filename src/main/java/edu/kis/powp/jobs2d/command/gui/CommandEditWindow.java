package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandEditWindow extends JFrame implements WindowComponent, Subscriber
{
    private CommandManager commandManager;
    private Container content;
    private GridBagConstraints gridBagConstraints;

    private JTextArea textArea;
    private JLabel saveResultResponse;
    private String noActionText = "No action taken";

    public CommandEditWindow(CommandManager commandManager) {
        this.setTitle("Command edit window");
        this.setSize(400, 400);
        content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;
        gridBagConstraints = new GridBagConstraints();

        JButton btnEditCommand = new JButton("Save current command");
        btnEditCommand.addActionListener((ActionEvent e) -> this.editCommand());
        gridBagConstraints.gridy = 0;
        content.add(btnEditCommand, gridBagConstraints);

        saveResultResponse = new JLabel(noActionText);
        gridBagConstraints.gridy = 1;
        content.add(saveResultResponse, gridBagConstraints);

        textArea = new JTextArea(CommandExporter.exportToText(commandManager.getCurrentCommand()));
        textArea.setEditable(true);
        gridBagConstraints.gridy = 2;
        content.add(textArea, gridBagConstraints);
    }

    private void editCommand() 
    {
        String input = textArea.getText();
        CommandImporter importedCommand = CommandFactory.interpretInput(input);

        if (importedCommand == null) {
            saveResultResponse.setText("Incorrect command, no changes applied");
        } else {
            commandManager.setCurrentCommand(importedCommand.getCommand(), importedCommand.getName());
            saveResultResponse.setText("Succesfully changed command");
        }
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() 
    {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
            saveResultResponse.setText(noActionText);
            textArea.setText(CommandExporter.exportToText(commandManager.getCurrentCommand()));
        }
    }

    @Override
    public void update() {
        textArea.setText(CommandExporter.exportToText(commandManager.getCurrentCommand()));
    }
}