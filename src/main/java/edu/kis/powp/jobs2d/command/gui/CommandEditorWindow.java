package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;
import edu.kis.powp.jobs2d.command.ToStringCommandVisitor;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CommandEditorWindow extends JFrame implements WindowComponent {
    private ICommandManager commandManager;
    private JTextArea currentCommandField;
    private JTextArea currentCommandBody;
    private JButton btnSaveCommand;
    
    private String currentCommand;
    public CommandEditorWindow(ICommandManager commandManager) {
        this.setTitle("Command Editor");
        this.setSize(500, 600);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        currentCommand = commandManager.getCurrentCommandString();
        currentCommandField = new JTextArea(currentCommand);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 0;
        c.weighty = 0;
        content.add(currentCommandField, c);
        
        btnSaveCommand = new JButton("Save command");
        btnSaveCommand.addActionListener((ActionEvent e) -> this.saveCommand());
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0;
        content.add(btnSaveCommand, c);
        
        currentCommandBody = new JTextArea();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 3;
        c.weighty = 1;
    	content.add(currentCommandBody,c);
        
        updateCurrentCommandField();
        updateCurrentCommandList();
        updateBtnSaveState();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }
    
    public void updateCurrentCommandList() {
    	DriverCommand cmd = commandManager.getCurrentCommand();
    	if(cmd == null) return;
    	
    	ToStringCommandVisitor toStringVisitor = new ToStringCommandVisitor();
        
        cmd.accept(toStringVisitor);
        
        currentCommandBody.setText(toStringVisitor.getCommandString());
    }
    
    public void updateBtnSaveState() {
    	if(commandManager.getCurrentCommand() != null) btnSaveCommand.setEnabled(true);
    	else btnSaveCommand.setEnabled(false);
    	
    }
    
    public void saveCommand() {
    	String fullCommand = currentCommandField.getText() + System.lineSeparator() + currentCommandBody.getText();
        CommandImporter importedCommand = CommandFactory.interpretInput(fullCommand);
        assert importedCommand != null;
        List<DriverCommand> commands = importedCommand.getCommand();
        commandManager.setCurrentCommand(commands, importedCommand.getName());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
