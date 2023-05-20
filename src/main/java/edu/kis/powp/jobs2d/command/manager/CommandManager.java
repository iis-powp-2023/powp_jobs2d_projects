package edu.kis.powp.jobs2d.command.manager;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CountingCommandVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class CommandManager {
    private DriverCommand currentCommand = null;

    private Publisher changePublisher = new Publisher();
    private CountingCommandVisitor countingVisitor = new CountingCommandVisitor();

    /**
     * Set current command.
     * 
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        commandList.accept(countingVisitor);
        changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     * 
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        setCurrentCommand(new ICompoundCommand() {
            List<DriverCommand> driverCommands = commandList;

            @Override
            public void execute(Job2dDriver driver) {
                driverCommands.forEach((c) -> c.execute(driver));
            }

            @Override
            public Iterator<DriverCommand> iterator() {
                return driverCommands.iterator();
            }

            @Override
            public String toString() {
                return name;
            }

            @Override
            public void accept(ICommandVisitor visitor){
                visitor.visit(this);
            }
        });

        getCurrentCommand().accept(countingVisitor);
    }

    public synchronized CountingCommandVisitor getCountingVisitor(){
        return countingVisitor;
    }

    /**
     * Return current command.
     * 
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }
}
