package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectTestImmutableComplexCommand implements ActionListener {
    private DriverManager driverManager;

    public SelectTestImmutableComplexCommand(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(3, 120));
        commands.add(new OperateToCommand(3, 120));
        commands.add(new OperateToCommand(120, -122));
        commands.add(new OperateToCommand(-99, 10));
        commands.add(new OperateToCommand(135, 60));
        commands.add(new OperateToCommand(-34, -149));
        commands.add(new OperateToCommand(3, 120));

        List<DriverCommand> commands1 = new ArrayList<>();
        commands1.add(new SetPositionCommand(0,0));
        commands1.add(new OperateToCommand(50,50));

        ImmutableComplexCommand nestedImmutableComplexCommand = new ImmutableComplexCommand(commands.iterator());

        commands1.add(nestedImmutableComplexCommand);

        ImmutableComplexCommand immutableComplexCommand = new ImmutableComplexCommand(commands1.iterator());

        commands.add(new OperateToCommand(500, 500));

        immutableComplexCommand.execute(driverManager.getCurrentDriver());

    }
}
