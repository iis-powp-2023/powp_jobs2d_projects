package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordFeature;
import edu.kis.powp.jobs2d.transformations.ScaleTransformationCommandVisitor;
import edu.kis.powp.jobs2d.transformations.Transformation;
import edu.kis.powp.jobs2d.transformations.TransformationCommandVisitor;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SelectTransformationVisitorTestOptionListener implements ActionListener {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public SelectTransformationVisitorTestOptionListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Testing CommandVisitor");
        logger.info("Scale Transformation");
        logger.info("+ Rotate Transformation");


        List<DriverCommand> transformCommands = new ArrayList<>(RecordFeature.getTransformCommands());
        RecordFeature.clearTransformRecording();
        //DrawerFeature.getDrawerController().clearPanel(); <-- Cleaning



        for (DriverCommand comand : transformCommands) {
            TransformationCommandVisitor scaleVisitor = new TransformationCommandVisitor(comand, TransformationFactory.getDoubleScale());
            TransformationCommandVisitor rotateVisitor = new TransformationCommandVisitor(comand, TransformationFactory.getClockwiseRotation());
            //logger.info("Command: "+comand.toString());
            comand.accept(scaleVisitor);
            comand.accept(rotateVisitor);
            comand.execute(DriverFeature.getDriverManager().getCurrentDriver());
        }

    }
}
