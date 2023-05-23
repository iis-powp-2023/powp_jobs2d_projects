package edu.kis.powp.jobs2d.command.transformation;

import edu.kis.powp.jobs2d.transformations.Transformation;

public class ScaleTransformationCommandVisitor implements Transformation {

    private final double scale;
    private int posX;
    private int posY;

    public ScaleTransformationCommandVisitor(double scale){
        this.scale = scale;
    }

    @Override
    public int calculateNewX(int x, int y) {
        return (int)(posX * scale);
    }

    @Override
    public int calculateNewY(int x, int y) {
        return (int)(posY * scale);
    }

}
