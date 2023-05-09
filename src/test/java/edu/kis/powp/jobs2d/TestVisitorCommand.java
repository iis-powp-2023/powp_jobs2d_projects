package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestVisitorCommand {
    public static void main(String[] args) {

        SetPositionCommand setPositionCommand = new SetPositionCommand(0, 0);
        OperateToCommand operateToCommand1 = new OperateToCommand(10, 0);
        OperateToCommand operateToCommand2 = new OperateToCommand(10, 10);
        OperateToCommand operateToCommand3 = new OperateToCommand(0, 10);
        OperateToCommand operateToCommand4 = new OperateToCommand(0, 0);


        List<DriverCommand> commands =  Arrays.asList(setPositionCommand, operateToCommand1, operateToCommand2, operateToCommand3, operateToCommand4);
        CommandVisitorCommander visitor = new CommandVisitorCommander();


        for (DriverCommand command : commands) {
            command.accept(visitor);
        }

        int operateToCount = visitor.getOperateToCount();
        int setPositionCount = visitor.getSetPositionCount();

        System.out.println("operateToCount: " + operateToCount + "\nsetPositionCount: " + setPositionCount);
    }
}
