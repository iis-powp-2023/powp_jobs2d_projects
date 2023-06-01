package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UsageManagerWindow extends JFrame implements WindowComponent {
    private JProgressBar usageBar;
    private JLabel usageLabel;
    private JTextField headDistanceField;
    private JTextField operatingDistanceField;
    private JLabel messageField;
    private JButton btnRefill;

    public UsageManagerWindow() {
        this.setTitle("Usage Manager");
        this.setSize(400, 200);
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        content.setLayout(new GridBagLayout());

        c.insets = new Insets(2, 2, 2, 2);

        c.gridy = 0;
        c.gridx = 0;
        content.add(new JLabel("Usage"), c);

        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        usageBar = new JProgressBar(0, 100);
        usageBar.setValue(100);
        content.add(usageBar, c);

        c.gridx = 3;
        usageLabel = new JLabel("%");
        content.add(usageLabel, c);

        c.gridy = 2;
        c.gridx = 0;
        content.add(new JLabel("Head Distance:"), c);

        c.gridx = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        headDistanceField = new JTextField("0");
        headDistanceField.setColumns(10);
        content.add(headDistanceField, c);

        c.gridy = 3;
        c.gridx = 0;
        c.weightx = 1;
        JLabel opdistance = new JLabel("Operating Distance:");
        opdistance.setSize(opdistance.getPreferredSize());
        content.add(opdistance, c);
        c.weightx = 0;

        c.gridx = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        operatingDistanceField = new JTextField("0");
        operatingDistanceField.setColumns(10);
        content.add(operatingDistanceField, c);

        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 4;
        messageField = new JLabel("WARNING", SwingConstants.CENTER);
        messageField.setForeground(Color.red);

        //messageField.setVisible(false);
        content.add(messageField, c);

        c.gridx = 4;
        btnRefill = new JButton("REFILL");
        btnRefill.addActionListener((ActionEvent e) -> this.refill());
        btnRefill.setEnabled(false);
        content.add(btnRefill, c);

        this.add(content, BorderLayout.LINE_START);
    }

    private void refill(){

    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
