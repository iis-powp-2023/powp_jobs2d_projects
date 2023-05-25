package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;

public class UsageManagerWindow extends JFrame implements WindowComponent {

    public UsageManagerWindow() {
        setTitle("Usage Manager");
        setSize(400, 400);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
    }


    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
