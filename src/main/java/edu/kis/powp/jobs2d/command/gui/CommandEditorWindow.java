package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;


import javax.swing.*;
import java.awt.*;

public class CommandEditorWindow extends JFrame implements WindowComponent {


    public CommandEditorWindow(ICommandManager commandManager) {
        this.setTitle("Command Editor");
        this.setSize(500, 600);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
