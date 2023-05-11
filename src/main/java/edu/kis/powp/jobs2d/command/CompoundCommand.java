package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CompoundCommand implements ICompoundCommand{

    private final List<DriverCommand> ListOfCommands;
    public CompoundCommand(List<DriverCommand> ListOfCommands){
        List<DriverCommand> tempList = new ArrayList<>();
        for (DriverCommand command: ListOfCommands) {
            if(command instanceof OperateToCommand){
                OperateToCommand obj = new OperateToCommand((OperateToCommand) command);
                tempList.add(obj);
            }
            else if(command instanceof SetPositionCommand){
                SetPositionCommand obj = new SetPositionCommand( (SetPositionCommand) command);
                tempList.add(obj);
            }
            else if(command instanceof ICompoundCommand){
                Iterator iterator = ((ICompoundCommand) command).iterator();
                while(iterator.hasNext()){
                    DriverCommand driverCommand = ((DriverCommand) iterator.next());
                    if(driverCommand instanceof OperateToCommand){
                        OperateToCommand operateToCommand = new OperateToCommand((OperateToCommand) driverCommand);
                        tempList.add(operateToCommand);
                    }
                    else if(driverCommand instanceof SetPositionCommand){
                        SetPositionCommand setPositionCommand = new SetPositionCommand( (SetPositionCommand) driverCommand);
                        tempList.add(setPositionCommand);
                    }
                }
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
