package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CompoundCommand implements ICompoundCommand{

    private final List<DriverCommand> ListOfCommands;
    public CompoundCommand(Iterator iterator){
        List<DriverCommand> tempList = new ArrayList<>();
        while(iterator.hasNext()){
            DriverCommand command = (DriverCommand) iterator.next();
            if(command instanceof OperateToCommand){
                OperateToCommand operateToCommand = new OperateToCommand((OperateToCommand) command);
                tempList.add(operateToCommand);
            }
            else if(command instanceof SetPositionCommand){
                SetPositionCommand setPositionCommand = new SetPositionCommand( (SetPositionCommand) command);
                tempList.add(setPositionCommand);
            }
            else if(command instanceof CompoundCommand){
                CompoundCommand compoundCommand = new CompoundCommand(((CompoundCommand) command).iterator());
                tempList.add(compoundCommand);
            }
        }
        this.ListOfCommands = List.copyOf(tempList);
    }
    @Override
    public void execute(Job2dDriver driver) {
        Iterator<DriverCommand> iterator = this.iterator();
        while(iterator.hasNext()){
            iterator.next().execute(driver);
        }
    }
    @Override
    public Iterator<DriverCommand> iterator() {
        return this.ListOfCommands.iterator();
    }
}
