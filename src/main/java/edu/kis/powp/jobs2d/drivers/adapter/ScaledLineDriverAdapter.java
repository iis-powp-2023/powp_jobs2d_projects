package edu.kis.powp.jobs2d.drivers.adapter;

public class ScaledLineDriverAdapter extends LineDriverAdapter{

    public ScaledLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
        super(lineDriverAdapter.getDrawController(), lineDriverAdapter.getLine(), lineDriverAdapter.getName());
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
