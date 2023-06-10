package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawerListener implements MouseListener {

    private final DriverManager driverManager;
    private final int panelWidth;
    private final int panelHeight;

    public MouseDrawerListener(DriverManager driverManager, int panelWidth, int panelHeight){
        this.driverManager = driverManager;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Job2dDriver currentDriver = driverManager.getCurrentDriver();
        if(e.getButton() == MouseEvent.BUTTON1){
            currentDriver.setPosition(e.getX() - (panelWidth / 2), e.getY() - (panelHeight / 2));
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            currentDriver.operateTo(e.getX() - (panelWidth / 2), e.getY() - (panelHeight / 2));
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
