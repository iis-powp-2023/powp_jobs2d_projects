package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawerListener implements MouseListener {

    private final DriverManager driverManager;

    public MouseDrawerListener(DriverManager driverManager){
        this.driverManager = driverManager;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Job2dDriver currentDriver = driverManager.getCurrentDriver();
        int xOffset = 268;
        int yOffset = 226;
        if(e.getButton() == MouseEvent.BUTTON1){
            currentDriver.setPosition(e.getX() - xOffset, e.getY() - yOffset);
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            currentDriver.operateTo(e.getX() - xOffset, e.getY() - yOffset);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
