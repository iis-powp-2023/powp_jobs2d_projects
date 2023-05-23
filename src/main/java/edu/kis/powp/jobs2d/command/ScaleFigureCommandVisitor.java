package edu.kis.powp.jobs2d.command;

public class ScaleFigureCommandVisitor extends FigureCommandVisitor {

    private final double scale;
    private int posX;
    private int posY;

    public ScaleFigureCommandVisitor(double scale){
        this.scale = scale;
    }

    private void setScaled(int x, int y){
        posX = (int)(posX * scale);
        posY = (int)(posY * scale);
    }

    @Override
    public void visit(SetPositionCommand command) {
        setScaled(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setScaled(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }
}
