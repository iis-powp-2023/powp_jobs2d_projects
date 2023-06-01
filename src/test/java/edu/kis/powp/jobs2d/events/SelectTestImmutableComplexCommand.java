package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.RecordFeature;

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

        ImmutableComplexCommand immutableComplexCommand = ComplexCommandFactory.nestedShape();

        CommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(immutableComplexCommand);
    }
}
