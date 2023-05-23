package edu.kis.powp.jobs2d.command;

public class RotateFigureCommandVisitor extends FigureCommandVisitor {

    private final double degree;
    private int posX;
    private int posY;

    public RotateFigureCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }

    private void setRotated(int x, int y){
        posX = (int) ((int)Math.cos(degree) * x - Math.sin(degree) * y);
        posY = (int) ((int)Math.sin(degree) * x + Math.cos(degree) * y);
    }

    @Override
    public void visit(SetPositionCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }
}
