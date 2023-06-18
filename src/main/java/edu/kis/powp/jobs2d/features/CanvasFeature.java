package edu.kis.powp.jobs2d.features;

public class CanvasFeature {
    private static double originX;
    private static double originY;
    private static double width;
    private static double height;

    public static double getOriginX() {
        return originX;
    }

    public static void setOriginX(double originX) {
        CanvasFeature.originX = originX;
    }

    public static double getOriginY() {
        return originY;
    }

    public static void setOriginY(double originY) {
        CanvasFeature.originY = originY;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        CanvasFeature.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        CanvasFeature.height = height;
    }
}
