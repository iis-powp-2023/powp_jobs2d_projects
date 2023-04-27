package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.Job2dDriverTest;
import edu.kis.powp.jobs2d.command.CommandVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.util.ArrayList;
import java.util.List;


public class Job2dVisitorTest {

    /**
     * Visitor test.
     */
    public static void main(String[] args) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand(-20, -50));
        commands.add(new OperateToCommand(-20, -50));
        commands.add(new OperateToCommand(-20, -40));
        commands.add(new OperateToCommand(-20, 50));
        commands.add(new OperateToCommand(-20, 50));

        CommandManager manager = new CommandManager();
        manager.setCurrentCommand(commands, "Visitor Test");

        System.out.println(manager.getCurrentCommandString());

        //Compound command test
        manager.getCurrentCommand().accept(new CommandVisitor());

        //SetPositionCommand test
        SetPositionCommand setPositionCommand = new SetPositionCommand(0, 0);
        setPositionCommand.accept(new CommandVisitor());

        //OperateToCommand test
        OperateToCommand operateToCommand = new OperateToCommand(0, 0);
        operateToCommand.accept(new CommandVisitor());

    }
}
