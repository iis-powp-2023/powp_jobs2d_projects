package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CountingCommandVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectVisitorTestOptionListener implements ActionListener {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        CountingCommandVisitor commandVisitor = new CountingCommandVisitor();
        command.accept(commandVisitor);
        logger.info("Visitor test");
        logger.info("Current command set to: " + command);
        logger.info("Total count of commands: " + commandVisitor.getCompoundCommandsCount());
        logger.info("Count of OperateTo commands: " + commandVisitor.getOperateToCommandsCount());
        logger.info("Count of SetPosition commands: " + commandVisitor.getSetPositionCommandsCount());
    }
}
