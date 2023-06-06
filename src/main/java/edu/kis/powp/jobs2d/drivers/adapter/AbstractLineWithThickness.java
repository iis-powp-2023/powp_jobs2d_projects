package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.shape.line.AbstractLine;

public class AbstractLineWithThickness extends AbstractLine {
    public AbstractLineWithThickness setThickness(float t) {
        this.thickness=t;
        return this;
    }

}
