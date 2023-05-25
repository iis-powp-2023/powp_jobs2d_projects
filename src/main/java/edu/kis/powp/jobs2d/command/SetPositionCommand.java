package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

    private int posX, posY;

    public SetPositionCommand(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    public SetPositionCommand(SetPositionCommand setPositionCommand){
        this(setPositionCommand.posX, setPositionCommand.posY);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition(posX, posY);
    }

    @Override
    public void accept(ICommandVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SetPositionCommand)) {
            return false;
        }

        SetPositionCommand c = (SetPositionCommand) o;

        return Integer.compare(posX, c.posX) == 0
                && Integer.compare(posY, c.posY) == 0;
    }

}
