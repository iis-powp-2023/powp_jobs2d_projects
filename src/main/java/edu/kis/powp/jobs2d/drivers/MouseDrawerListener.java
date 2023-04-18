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
        System.out.println(e.getButton() + " mouse clicked X:" + e.getX() + " Y: " + e.getY());
        currentDriver = driverManager.getCurrentDriver();
        if(e.getButton() == MouseEvent.BUTTON1){
            currentDriver.setPosition(e.getY(), e.getX());
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            currentDriver.operateTo(e.getY(), e.getX());
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
