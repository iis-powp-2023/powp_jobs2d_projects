package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.RecordFeature;

public class SelectStartRecordingOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //start recording
        RecordFeature.setRecording();
    }
}
