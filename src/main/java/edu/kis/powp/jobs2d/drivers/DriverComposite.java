package edu.kis.powp.jobs2d.drivers;

import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class DriverComposite implements Job2dDriver {

    private List<Job2dDriver> drivers;

    @Override
    public void setPosition(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public void operateTo(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'operateTo'");
    }

}
