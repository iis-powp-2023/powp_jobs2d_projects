package edu.kis.powp.jobs2d.usage;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class UsageManagerWindow extends JFrame implements WindowComponent {
    private JProgressBar usageBar;
    private JTextField headDistanceField;
    private JTextField operatingDistanceField;
    private JTextField currentServiceIntervalField;
    private JLabel warningField;
    private JButton btnService;

    private UsageManager currentUsageManager = null;

    public void updateHeadDistanceField(String headDistance){
        this.headDistanceField.setText(headDistance);
    }

    public void updateOperatingDistanceField(String operatingDistance){
        this.operatingDistanceField.setText(operatingDistance);
    }

    public UsageManagerWindow() {
        this.setTitle("Usage Manager");
        this.setSize(580, 220);
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        content.setLayout(new GridBagLayout());

        c.insets = new Insets(3, 3, 3, 3);

        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 3;
        warningField = new JLabel("WARNING", SwingConstants.CENTER);
        warningField.setForeground(Color.red);
        warningField.setVisible(false);
        content.add(warningField, c);

        c.gridy = 1;
        c.gridx = 0;
        content.add(new JLabel("Usage:"), c);

        c.gridx = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        usageBar = new JProgressBar(0, 100);
        usageBar.setValue(100);
        usageBar.setStringPainted(true);
        usageBar.setForeground(Color.green);
        content.add(usageBar, c);

        c.gridy = 3;
        c.gridx = 0;
        content.add(new JLabel("Head distance:"), c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        headDistanceField = new JTextField("0");
        headDistanceField.setColumns(10);
        headDistanceField.setEditable(false);
        content.add(headDistanceField, c);

        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 1;
        content.add(new JLabel("Operating distance:"), c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        operatingDistanceField = new JTextField("0");
        operatingDistanceField.setColumns(10);
        operatingDistanceField.setEditable(false);
        content.add(operatingDistanceField, c);

        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 1;
        content.add(new JLabel("Current service interval:"), c);

        c.gridx = 1;
        c.gridwidth = 1;
        currentServiceIntervalField = new JTextField("10000");
        currentServiceIntervalField.setColumns(10);
        currentServiceIntervalField.setEditable(false);
        content.add(currentServiceIntervalField, c);

        c.gridx = 2;
        c.gridwidth = 1;
        btnService = new JButton("SERVICE");
        btnService.addActionListener((ActionEvent e) -> this.service());
        btnService.setEnabled(false);
        content.add(btnService, c);

        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 1;
        content.add(new JLabel("New service interval:"), c);

        c.gridx = 1;
        c.gridwidth = 1;
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField newServiceIntervalField = new JFormattedTextField(formatter);
        newServiceIntervalField.setColumns(10);
        content.add(newServiceIntervalField, c);

        c.gridx = 2;
        c.gridwidth = 1;
        JButton btnSetInterval = new JButton("SET NEW INTERVAL");
        btnSetInterval.addActionListener((ActionEvent e) -> {
            if(currentUsageManager == null)
                return;
            String textFromField = newServiceIntervalField.getText().replaceAll(",","");
            int newServiceInterval = Integer.parseInt(textFromField);
            if(newServiceInterval == 0){
                JOptionPane.showMessageDialog(this,
                        "Please enter value greater than 0",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentUsageManager.setMaxServiceInterval(newServiceInterval);
            currentServiceIntervalField.setText(String.valueOf(newServiceInterval));
            updateUsageWindow(currentUsageManager.getDeviceUsageState(), currentUsageManager.getDeviceUsage());
        });
        content.add(btnSetInterval, c);

        this.add(content, BorderLayout.CENTER);
    }

    private void service(){
        if(currentUsageManager == null)
            return;
        currentUsageManager.setServiceInterval(currentUsageManager.getMaxServiceInterval());
        updateUsageWindow(DeviceUsageState.UNUSED, 1.0);
    }

    public void updateUsageWindow(DeviceUsageState deviceUsageState, double deviceUsage){
        btnService.setEnabled(deviceUsageState != DeviceUsageState.UNUSED);
        switch (deviceUsageState){
            case UNUSED: updateUsageBar(Color.green, null); break;
            case LOW_USED: updateUsageBar(Color.yellow, null); break;
            case MEDIUM_USED: updateUsageBar(Color.orange, "Will need service soon"); break;
            case HIGH_USED: updateUsageBar(Color.red, "Needs service"); break;
            case INOPERABLE: updateUsageBar(Color.red, "Needs service - Device stopped"); break;
        }
        usageBar.setValue((int) (deviceUsage * 100));
    }

    private void updateUsageBar(Color barColor, String warningText){
        usageBar.setForeground(barColor);
        warningField.setVisible(warningText != null);
        if(warningText != null){
            warningField.setText(warningText);
        }
    }

    void setCurrentUsageManager(UsageManager usageManager){
        currentUsageManager = usageManager;
        currentServiceIntervalField.setText(String.valueOf(usageManager.getMaxServiceInterval()));
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
