package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawerListener implements MouseListener {

    private DriverManager driverManager;
    private Job2dDriver currentDriver;

    public MouseDrawerListener(DriverManager driverManager){
        this.driverManager = driverManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentDriver = driverManager.getCurrentDriver();
        if(e.getButton() == MouseEvent.BUTTON1){
            currentDriver.setPosition(e.getX(), e.getY());
        }
        else if(e.getButton() == MouseEvent.BUTTON2){
            currentDriver.operateTo(e.getX(), e.getY());
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
