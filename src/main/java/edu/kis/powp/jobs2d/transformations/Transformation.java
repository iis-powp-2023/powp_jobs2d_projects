package edu.kis.powp.jobs2d.transformations;

public interface Transformation {
    int calculateNewX(int x, int y);
    int calculateNewY(int x, int y);
}
