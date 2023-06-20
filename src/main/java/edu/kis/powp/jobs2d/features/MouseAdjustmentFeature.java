package edu.kis.powp.jobs2d.features;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseAdjustmentFeature implements MouseWheelListener {

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() > 0){
            System.out.println("Scroll Down");
        }
        else{
            System.out.println("Scroll Up");
        }
    }
}
