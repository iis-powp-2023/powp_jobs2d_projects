package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;

public class LineFactoryWithThinLine extends LineFactory {
    public static ILine getBasicThinLine() {
        return new AbstractLineWithThickness().setThickness(1F);
    }

}