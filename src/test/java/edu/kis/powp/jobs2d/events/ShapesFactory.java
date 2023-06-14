package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapesFactory {

    static public ImmutableCompoundCommand triangle(Point2D p1, Point2D p2, Point2D p3) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand((int) p1.getX(), (int) p1.getY()));
        commands.add(new OperateToCommand((int) p2.getX(), (int) p2.getY()));
        commands.add(new OperateToCommand((int) p3.getX(), (int) p3.getY()));
        commands.add(new OperateToCommand((int) p1.getX(), (int) p1.getY()));
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("triangle");
        builder.addCommands(commands);

        return builder.build();
    }


    static public ImmutableCompoundCommand rectangle(Point2D leftBottomPoint, Point2D rightUpperPoint) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand((int) leftBottomPoint.getX(), (int) leftBottomPoint.getY()));
        commands.add(new OperateToCommand((int) rightUpperPoint.getX(), (int) leftBottomPoint.getY()));
        commands.add(new OperateToCommand((int) rightUpperPoint.getX(), (int) rightUpperPoint.getY()));
        commands.add(new OperateToCommand((int) leftBottomPoint.getX(), (int) rightUpperPoint.getY()));

        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("rectangle");
        builder.addCommands(commands);

        return builder.build();
    }


    static public ImmutableCompoundCommand quadrangle(List<Point2D> point2DList) {

        List<DriverCommand> commands = new ArrayList<DriverCommand>();

        commands.add(new SetPositionCommand((int) point2DList.get(0).getX(), (int) point2DList.get(0).getY()));
        for (Point2D point : point2DList.subList(1, point2DList.size())) {
            commands.add(new OperateToCommand((int) point.getX(), (int) point.getY()));
        }
        commands.add(new OperateToCommand((int) point2DList.get(0).getX(), (int) point2DList.get(0).getY()));
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("quadrangle");
        builder.addCommands(commands);

        return builder.build();
    }

    static public ImmutableCompoundCommand topSecretCommand() {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand(-20, -50));
        commands.add(new OperateToCommand(-20, -50));
        commands.add(new SetPositionCommand(-20, -40));
        commands.add(new OperateToCommand(-20, 50));
        commands.add(new SetPositionCommand(0, -50));
        commands.add(new OperateToCommand(0, -50));
        commands.add(new SetPositionCommand(0, -40));
        commands.add(new OperateToCommand(0, 50));
        commands.add(new SetPositionCommand(70, -50));
        commands.add(new OperateToCommand(20, -50));
        commands.add(new OperateToCommand(20, 0));
        commands.add(new OperateToCommand(70, 0));
        commands.add(new OperateToCommand(70, 50));
        commands.add(new OperateToCommand(20, 50));


        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("topSecretCommand");
        builder.addCommands(commands);

        return builder.build();

    }


}
