package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.observer.Subscriber;

import edu.kis.powp.jobs2d.command.DriverCommand;

//import edu.kis.powp.jobs2d.command.gui.CommandImporter;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class CommandManagerWindow extends JFrame implements WindowComponent {

    private CommandManager commandManager;

    private JTextArea currentCommandField;

    private String observerListString;
    private JTextArea observerListField;

    private JTextArea textInput;

    /**
     * 
     */
    private static final long serialVersionUID = 9204679248304669948L;

    public CommandManagerWindow(CommandManager commandManager) {
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
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        textInput = new JTextArea("Write here for command import");
        textInput.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(textInput, c);

        JButton btnImportCommand = new JButton("Import command");
        btnImportCommand.addActionListener((ActionEvent e) -> this.importCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnImportCommand, c);

        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);
    }

    private void importCommand() {
        
        try
        {
            String input = textInput.getText();
            if (input.equals(""))
            {
                textInput.setText("Write here for command import");
                return;
            }
            else if (input.equals("Write here for command import"))
            {
                return;
            }

            if (input.substring(input.length() - 3, input.length()).equals("txt"))
            {
                
                try
                {
                    List<DriverCommand> command =  CommandImporter.fromTextfile(input);
                    File fileObject = new File(input);
                    Scanner scanner = new Scanner(fileObject);
                    String name = scanner.nextLine();
                    scanner.close();
                    if (command == null)
                    {
                        input = "This command was incorrect\n" + input;
                        textInput.setText(input);
                        return;
                    }

                    commandManager.setCurrentCommand(command, name);
                    textInput.setText("Write here for command import");
                }
                catch (FileNotFoundException e)
                {
                    input = "So such text file was found\n" + input;
                    textInput.setText(input);
                    e.printStackTrace();
                    return;
                }
            }
            else
            {
                input += "\n";
                List<DriverCommand> command =  CommandImporter.fromText(input);
                Scanner scanner = new Scanner(input);
                String name = scanner.nextLine();
                scanner.close();
                if (command == null)
                {
                    input = "This command was incorrect\n" + input;
                    textInput.setText(input);
                    return;
                }

                commandManager.setCurrentCommand(command, name);
                textInput.setText("Write here for command import");
            }         
        }
        catch(Exception e)
        {
            String input = "No command was loaded\n" +  textInput.getText() + "\n";;
            textInput.setText(input);
            e.printStackTrace();
        }
        
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
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
