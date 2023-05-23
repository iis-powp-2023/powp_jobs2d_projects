package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;

public class ScaledLineDriverAdapter extends LineDriverAdapter{

    public ScaledLineDriverAdapter(DrawPanelController drawController, ILine line, String name) {
        super(drawController,line,name);
    }

    private double scale=1;

    @Override
    public void setPosition(int x, int y) {
        super.setPosition((int) (x*scale), (int) (y*scale));
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo((int) (x*scale), (int) (y*scale));
        this.setPosition(x, y);

    }
    public LineDriverAdapter setScale(double k)
    {
        if (k>=0)
        {
            this.scale=k;
        }
        return this;
    }
}
