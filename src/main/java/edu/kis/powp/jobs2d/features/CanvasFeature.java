package edu.kis.powp.jobs2d.features;

import java.awt.*;

public class CanvasFeature {
    private static Shape shape;

    public static Shape getShape() {
        return shape;
    }

    public static void setShape(Shape shape) {
        CanvasFeature.shape = shape;
    }
}
