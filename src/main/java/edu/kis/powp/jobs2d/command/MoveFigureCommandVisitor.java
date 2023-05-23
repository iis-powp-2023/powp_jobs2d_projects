package edu.kis.powp.jobs2d.command;

public class MoveFigureCommandVisitor extends FigureCommandVisitor {

    private final int offX;
    private final int offY;
    private int posX;
    private int posY;

    public MoveFigureCommandVisitor(int offX, int offY){
        this.offX = offX;
        this.offY = offY;
    }

    private void setMoved(int x, int y){
        posX = x + offX;
        posY = y + offY;
    }

    @Override
    public void visit(SetPositionCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }
}

