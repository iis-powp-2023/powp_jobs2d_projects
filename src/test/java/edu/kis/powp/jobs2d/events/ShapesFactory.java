package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapesFactory {

    static public ImmutableCompoundCommand triangle(Point2D p1, Point2D p2, Point2D p3)
    {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand((int)p1.getX(), (int) p1.getY()));
        commands.add(new OperateToCommand((int)p2.getX(), (int) p2.getY()));
        commands.add(new OperateToCommand((int)p3.getX(), (int) p3.getY()));
        commands.add(new OperateToCommand((int)p1.getX(),(int) p1.getY()));
        return new ImmutableCompoundCommand(commands);
    }


    static public ImmutableCompoundCommand rectangle(Point2D leftBottomPoint, Point2D rightUpperPoint)
    {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new SetPositionCommand((int)leftBottomPoint.getX(), (int) leftBottomPoint.getY()));
        commands.add(new OperateToCommand((int)rightUpperPoint.getX(), (int) leftBottomPoint.getY()));
        commands.add(new OperateToCommand((int)rightUpperPoint.getX(), (int) rightUpperPoint.getY()));
        commands.add(new OperateToCommand((int)leftBottomPoint.getX(),(int) rightUpperPoint.getY()));
        return new ImmutableCompoundCommand(commands);
    }



    static public ImmutableCompoundCommand quadrangle(List<Point2D> point2DList)
    {

        List<DriverCommand> commands = new ArrayList<DriverCommand>();

        commands.add(new SetPositionCommand((int)point2DList.get(0).getX(), (int) point2DList.get(0).getY()));
        for(Point2D point : point2DList.subList(1,point2DList.size()))
        {
            commands.add(new OperateToCommand((int)point.getX(), (int) point.getY()));
        }
        commands.add(new OperateToCommand((int)point2DList.get(0).getX(),(int) point2DList.get(0).getY()));
        return new ImmutableCompoundCommand(commands);
    }


}
