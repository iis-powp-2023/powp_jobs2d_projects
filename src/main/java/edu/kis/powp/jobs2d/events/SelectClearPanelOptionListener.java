package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.drivers.decorator.CanvasDriver;
import edu.kis.powp.jobs2d.features.CanvasFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.RecordFeature;

public class SelectClearPanelOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordFeature.clearTransformRecording();
        RecordFeature.clearCanvas();
        DrawerFeature.getDrawerController().clearPanel();
    }
}
