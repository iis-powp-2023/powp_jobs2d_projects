package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;

public class BookmarksWindow extends JFrame implements WindowComponent
{
    private Bookmarks bookmarks;
    private Container content;
    private GridBagConstraints gridBagConstraints;

    private Integer rowNumber = 0;
    private JTextArea textInput;
    private String defaultTextInputMessage = "Write here to add description to a bookmark";

    public BookmarksWindow() {
        bookmarks = Bookmarks.getInstance();
        this.setTitle("Bookmarks");
        this.setSize(800, 400);
        content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();

        JButton btnAddBookmark = new JButton("Make a bookmark of the current command");
        btnAddBookmark.addActionListener((ActionEvent e) -> Bookmarks.getInstance().bookmarkCurrentCommand());
        gridBagConstraints.gridy = 0;
        content.add(btnAddBookmark, gridBagConstraints);

        textInput = new JTextArea(defaultTextInputMessage);
        textInput.setEditable(true);
        gridBagConstraints.gridy = 1;
        content.add(textInput, gridBagConstraints);
    }

    public void displayFailiureStatus()
    {
        String errorResponse = "Could not save the current command\n" + defaultTextInputMessage;
        textInput.setText(errorResponse);
    }

    public String getDescription()
    {
        return textInput.getText();
    }

    public void addBookmark(String name, String description, CommandLoaderListener action) 
    {
        try
        {
            JTextArea bookmarkName = new JTextArea(name);
            gridBagConstraints.weightx = 1;
            gridBagConstraints.gridy = rowNumber + 2;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.weighty = 1;
            content.add(bookmarkName, gridBagConstraints);
            
            JTextArea bookmarkDescription = new JTextArea(description);
            gridBagConstraints.gridx = 1;
            content.add(bookmarkDescription, gridBagConstraints);

            JButton btnAddBookmark = new JButton("Load command");
            btnAddBookmark.addActionListener(action);
            gridBagConstraints.gridx = 2;
            content.add(btnAddBookmark, gridBagConstraints);

            rowNumber++;
            SwingUtilities.updateComponentTreeUI(this);
            textInput.setText(defaultTextInputMessage);
        }
        catch (Exception e)
        {
            displayFailiureStatus();
        }        
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() 
    {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
            textInput.setText(defaultTextInputMessage);
        }
    }
}