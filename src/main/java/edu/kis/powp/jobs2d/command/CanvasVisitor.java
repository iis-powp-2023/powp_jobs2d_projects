package edu.kis.powp.jobs2d.command;

import java.awt.*;
import java.util.Iterator;

public class CanvasVisitor implements ICommandVisitor{

    private Shape canvas;

    public CanvasVisitor(Shape shape) {
        this.canvas = shape;
    }

    @Override
    public void visit(ICompoundCommand command) {
        Iterator<DriverCommand> iterator = command.iterator();

        while (iterator.hasNext()) {
            iterator.next().accept(this);
        }
    }

    @Override
    public void visit(OperateToCommand command) {
        if (!canvas.contains(command.getPosX(), command.getPosY())) {
            throw new IllegalArgumentException("Command exceeds canvas boundaries!");
        }
    }

    @Override
    public void visit(SetPositionCommand command) {
        if (!canvas.contains(command.getPosX(), command.getPosY())) {
            throw new IllegalArgumentException("Command exceeds canvas boundaries!");
        }
    }
}
