package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ToStringCommandVisitor;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;


import javax.swing.*;
import java.awt.*;

public class CommandEditorWindow extends JFrame implements WindowComponent {
    private ICommandManager commandManager;
    private JTextArea currentCommandField;
    private JTextArea currentCommandBody;
    
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
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        
        currentCommandBody = new JTextArea();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridy = 1;
        c.weighty = 1;
    	content.add(currentCommandBody,c);
        
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
        updateCurrentCommandList();
    }
    
    public void updateCurrentCommandList() {
    	DriverCommand cmd = commandManager.getCurrentCommand();
    	if(cmd == null) return;
    	
    	ToStringCommandVisitor toStringVisitor = new ToStringCommandVisitor();
        
        cmd.accept(toStringVisitor);
        
        currentCommandBody.setText(toStringVisitor.getCommandString());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
