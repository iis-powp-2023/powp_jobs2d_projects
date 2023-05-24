package edu.kis.powp.jobs2d.transformations;


import java.awt.Dimension;

public class WindowSizeVisitor {
    private Dimension size;

    public void visitA4() {
        size = new Dimension(794, 1123);
    }

    public void visitA5() {
        size = new Dimension(559, 794);
    }

    public Dimension getSize() {
        return size;
    }
}
