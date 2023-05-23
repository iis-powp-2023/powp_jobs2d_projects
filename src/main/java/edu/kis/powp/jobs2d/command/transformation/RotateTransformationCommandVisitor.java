package edu.kis.powp.jobs2d.command.transformation;

import edu.kis.powp.jobs2d.transformations.Transformation;

public class RotateTransformationCommandVisitor implements Transformation {

    private final double degree;

    public RotateTransformationCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }


    @Override
    public int calculateNewX(int x, int y) {
        return (int) ((int)Math.cos(degree) * x - Math.sin(degree) * y);
    }

    @Override
    public int calculateNewY(int x, int y) {
        return (int) ((int)Math.sin(degree) * x + Math.cos(degree) * y);
    }
}
