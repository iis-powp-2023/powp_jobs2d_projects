package edu.kis.powp.jobs2d.command.transformation;

import edu.kis.powp.jobs2d.transformations.Transformation;

public class MoveTransformationCommandVisitor implements Transformation {

    private final int offX;
    private final int offY;


    public MoveTransformationCommandVisitor(int offX, int offY){
        this.offX = offX;
        this.offY = offY;
    }

    @Override
    public int calculateNewX(int x, int y) {
        return x + offX;
    }

    @Override
    public int calculateNewY(int x, int y) {
        return y + offY;
    }
}

