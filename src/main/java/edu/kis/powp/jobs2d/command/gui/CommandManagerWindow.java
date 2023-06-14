package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.sound.sampled.Line;
import javax.swing.*;


import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;



public class CommandManagerWindow extends JFrame implements WindowComponent {

    private CommandManager commandManager;
    private JTextArea currentCommandField;
    private String observerListString;
    private JTextArea observerListField;
    private JPanel iconJPanel;
    private DrawPanelController iconDraw;
    private JTextArea textInput;
    private String defaultTextInputMessage = "Write here for command import";
    private BookmarksWindow bookmarks = null;
    private static final long serialVersionUID = 9204679248304669948L;
    private final Job2dDriver driverCommandPreview;
    public CommandManagerWindow(CommandManager commandManager) 
    {
        bookmarks = new BookmarksWindow(commandManager);
        
        this.setTitle("Command Manager");

        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();


        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.gridy = 1;
        c.weighty = 2;
        c.weightx=0.5;
        content.add(currentCommandField, c);
        updateCurrentCommandField();



        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        updateObserverListField();
        content.add(panel, c);
        iconDraw=new DrawPanelController();
        iconDraw.initialize(panel);
        driverCommandPreview = new LineDriverAdapter(iconDraw, LineFactory.getBasicLine(), "basic");



        textInput = new JTextArea(defaultTextInputMessage);
        textInput.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 2;
        c.weighty = 1;
        content.add(textInput, c);

        JButton btnImportCommand = new JButton("Import command");
        btnImportCommand.addActionListener((ActionEvent e) -> this.importCommand());
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        content.add(btnImportCommand, c);


        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 4;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 5;
        c.weighty = 1;
        content.add(btnClearObservers, c);

        JButton btnBookmarks = new JButton("Bookmarks");
        btnBookmarks.addActionListener((ActionEvent e) -> this.openBookmarks());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 6;
        c.weighty = 1;
        content.add(btnBookmarks, c);
    }

    private void openBookmarks() 
    {
        bookmarks.HideIfVisibleAndShowIfHidden();
    }

    private void importCommand() {
        String input = textInput.getText();
        if (input.equals(""))
        {
            textInput.setText(defaultTextInputMessage);
            return;
        }
        else if (input.equals(defaultTextInputMessage))
        {
            return;
        }

        CommandImporter importedCommand = CommandFactory.interpretInput(input);

        if (importedCommand == null)
        {
            input = "Could not import command:\n" + input;
            textInput.setText(input);
        }
        else
        {
            commandManager.setCurrentCommand(importedCommand.getCommand(), importedCommand.getName());
            textInput.setText(defaultTextInputMessage);
        }        
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void updateCurrentCommandPreview()
    {
        iconDraw.clearPanel();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        command.execute(driverCommandPreview);
    }
    public void deleteObservers() {
        commandManager.getChangePublisher().clearObservers();
        this.updateObserverListField();
    }

    private void updateObserverListField() {
        observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        observerListField.setText(observerListString);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        updateObserverListField();
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

}
