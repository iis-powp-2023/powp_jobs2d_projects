package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;

public class BookmarksWindow extends JFrame implements WindowComponent
{
    private CommandManager commandManager;
    private Container content;
    private GridBagConstraints gridBagConstraints;

    private Integer rowNumber = 0;
    private JTextArea textInput;
    private String defaultTextInputMessage = "Write here to add description to a bookmark";

    public BookmarksWindow(CommandManager commandManager) {
        this.setTitle("Bookmarks");
        this.setSize(800, 400);
        content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;
        gridBagConstraints = new GridBagConstraints();

        JButton btnAddBookmark = new JButton("Make a bookmark of the current command");
        btnAddBookmark.addActionListener((ActionEvent e) -> this.addBookmark());
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridy = 0;
        content.add(btnAddBookmark, gridBagConstraints);

        textInput = new JTextArea(defaultTextInputMessage);
        textInput.setEditable(true);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridy = 1;
        content.add(textInput, gridBagConstraints);
    }

    private void addBookmark() 
    {
        DriverCommand currentCommand = commandManager.getCurrentCommand();

        try
        {
            JTextArea bookmarkName = new JTextArea(currentCommand.toString());
            gridBagConstraints.weightx = 1;
            gridBagConstraints.gridy = rowNumber + 2;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.gridx = 0;
            content.add(bookmarkName, gridBagConstraints);

            String description = textInput.getText();
            textInput.setText(defaultTextInputMessage);
            JTextArea bookmarkDescription = new JTextArea(description);
            gridBagConstraints.weightx = 1;
            gridBagConstraints.gridy = rowNumber + 2;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.gridx = 1;
            content.add(bookmarkDescription, gridBagConstraints);

            JButton btnAddBookmark = new JButton("Load command");
            btnAddBookmark.addActionListener(new CommandLoader(null, commandManager));  //CHANGE
            gridBagConstraints.weightx = 1;
            gridBagConstraints.gridy = rowNumber + 2;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.gridx = 2;
            content.add(btnAddBookmark, gridBagConstraints);
            rowNumber++;
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception e)
        {
            String errorResponse = "Could not save the current command\n" + defaultTextInputMessage;
            textInput.setText(errorResponse);
        }        
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() 
    {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
}