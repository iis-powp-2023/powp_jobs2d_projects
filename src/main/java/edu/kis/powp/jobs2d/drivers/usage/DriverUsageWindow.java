package edu.kis.powp.jobs2d.drivers.usage;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.drivers.decorator.RecordingDriver;

public class DriverUsageWindow extends JFrame implements WindowComponent {

    private Job2dDriver currentDriver;
    private JTextArea currentDriverName;
    private JProgressBar inkRemaining;
    private JProgressBar headDurabilityRemaining;
    private JButton btnRefill;
    private JButton btnServiceHead;

    public DriverUsageWindow() {
        this.setTitle("Driver usage Visualisation");

        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        currentDriverName = new JTextArea("Driver name placeholder");
        currentDriverName.setEditable(false);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridy = 0;
        c.weighty = 1;
        content.add(currentDriverName, c);

        inkRemaining = new JProgressBar();
        inkRemaining.setStringPainted(true);
        inkRemaining.setValue(100);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridy = 1;
        c.weighty = 1;
        content.add(inkRemaining, c);

        headDurabilityRemaining = new JProgressBar();
        headDurabilityRemaining.setStringPainted(true);
        headDurabilityRemaining.setValue(100);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridy = 2;
        c.weighty = 1;
        content.add(headDurabilityRemaining, c);

        btnRefill = new JButton("Refill");
        btnRefill.addActionListener((ActionEvent e) -> {
            if(this.currentDriver instanceof DistanceCountingDriver) {
                ((DistanceCountingDriver) this.currentDriver).getDeviceUsageManager().setOperatingDistance(0);
                this.setInkRemaining(100);
            } else if(this.currentDriver instanceof RecordingDriver && ((RecordingDriver) this.currentDriver).getDriver() instanceof DistanceCountingDriver) {
                ((DistanceCountingDriver) ((RecordingDriver) this.currentDriver).getDriver()).getDeviceUsageManager().setOperatingDistance(0);
                this.setInkRemaining(100);
            }
        });
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 1;
        content.add(btnRefill, c);

        btnServiceHead = new JButton("Service head");
        btnServiceHead.addActionListener((ActionEvent e) -> {
            if(this.currentDriver instanceof DistanceCountingDriver) {
                ((DistanceCountingDriver) this.currentDriver).getDeviceUsageManager().setHeadDistance(0);
                this.setHeadDurabilityRemaining(100);
            } else if(this.currentDriver instanceof RecordingDriver && ((RecordingDriver) this.currentDriver).getDriver() instanceof DistanceCountingDriver) {
                ((DistanceCountingDriver) ((RecordingDriver) this.currentDriver).getDriver()).getDeviceUsageManager().setHeadDistance(0);
                this.setHeadDurabilityRemaining(100);
            }
        });
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        c.weighty = 1;
        content.add(btnServiceHead, c);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

    public void setCurrentDriver(Job2dDriver driver) {
        this.currentDriver = driver;
        this.currentDriverName.setText(driver.toString());

        if(this.currentDriver instanceof DistanceCountingDriver) {
            this.setInkRemaining(100 - (int)(((DistanceCountingDriver) this.currentDriver).getDeviceUsageManager().getOperatingDistance() / ServiceThresholds.REFILL_THRESHOLD.getValue() * 100));
            this.setHeadDurabilityRemaining(100 - (int)(((DistanceCountingDriver) this.currentDriver).getDeviceUsageManager().getHeadDistance() / ServiceThresholds.HEAD_REPLACE_THRESHOLD.getValue() * 100));
        } else if(this.currentDriver instanceof RecordingDriver && ((RecordingDriver) this.currentDriver).getDriver() instanceof DistanceCountingDriver) {
            this.setInkRemaining(100 - (int)(((DistanceCountingDriver) ((RecordingDriver) this.currentDriver).getDriver()).getDeviceUsageManager().getOperatingDistance() / ServiceThresholds.REFILL_THRESHOLD.getValue() * 100));
            this.setHeadDurabilityRemaining(100 - (int)(((DistanceCountingDriver) ((RecordingDriver) this.currentDriver).getDriver()).getDeviceUsageManager().getHeadDistance() / ServiceThresholds.HEAD_REPLACE_THRESHOLD.getValue() * 100));
        } else {
            //skip,
            //either composite or Jobs2dDriver class instance (without inheritance of DistanceCountingDriver)
        }
    }

    public void setInkRemaining(int inkRemainingPercentage) {
        this.inkRemaining.setValue(inkRemainingPercentage);
    }

    public void setHeadDurabilityRemaining(int headDurabilityPercentage) {
        this.headDurabilityRemaining.setValue(headDurabilityPercentage);
    }
}
