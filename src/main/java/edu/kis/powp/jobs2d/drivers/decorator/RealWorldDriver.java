package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.Iterator;

public class RealWorldDriver extends DriverDecorator {

    private double operateToSpeed;
    private double setPositionSpeed;
    private double operateToSetDelay;
    private double setToOperateDelay;
    private int lastX = 0;
    private int lastY = 0;
    private int partsNum = 10;

    private enum OperationType {
        OPERATE_TO,
        SET_POSITION
    }

    private OperationType lastOperationType = OperationType.SET_POSITION;

    public RealWorldDriver(Job2dDriver driver, double operateToSpeed, double setPositionSpeed, double operateToSetDelay,
            double setToOperateDelay) {
        super(driver);
        this.operateToSpeed = operateToSpeed;
        this.setPositionSpeed = setPositionSpeed;
        this.operateToSetDelay = operateToSetDelay;
        this.setToOperateDelay = setToOperateDelay;
    }

    public RealWorldDriver(Job2dDriver driver, double operateToSpeed) {
        super(driver);
        this.operateToSpeed = operateToSpeed;
        this.setPositionSpeed = 3 * operateToSpeed;
        this.operateToSetDelay = 1.0;
        this.setToOperateDelay = 1.0;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        try {
            if (lastOperationType == OperationType.OPERATE_TO) {
                Thread.sleep((long) (operateToSetDelay * 1000));
            }
            lastOperationType = OperationType.SET_POSITION;
            Thread.sleep((long) (Math.sqrt(Math.pow(x - lastX, 2) + Math.pow(y - lastY, 2)) / setPositionSpeed * 1000));
            lastX = x;
            lastY = y;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void operateTo(int x, int y) {
        try {
            if (lastOperationType == OperationType.SET_POSITION) {
                Thread.sleep((long) (setToOperateDelay * 1000));
            }
            lastOperationType = OperationType.OPERATE_TO;
            LinePartitions operationData = calculateDelays(x, y);
            Double delay = operationData.getDelay();
            for (Point2D.Double point : operationData) {
                Thread.sleep(delay.intValue());
                super.operateTo((int) point.x, (int) point.y);
            }
            lastX = x;
            lastY = y;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Diver simulating real world time";
    }

    private LinePartitions calculateDelays(int destinationX, int destinationY) {
        ArrayList<Point2D.Double> pointsList = new ArrayList<>();
        double distance = Math.sqrt(Math.pow(lastY - destinationY, 2) + Math.pow(lastX - destinationX, 2));
        double delay = (distance / operateToSpeed) / partsNum;
        for (int i = 1; i <= partsNum; i++) {
            Point2D.Double point = new Point2D.Double();
            point.x = lastX + (double) (destinationX - lastX) / partsNum * i;
            point.y = lastY + (double) (destinationY - lastY) / partsNum * i;
            pointsList.add(point);
        }

        return new LinePartitions(delay, pointsList);
    }

    private class LinePartitions implements Iterable<Point2D.Double> {
        private double delay;
        private ArrayList<Point2D.Double> points;

        public LinePartitions(double delay, ArrayList<Point2D.Double> points) {
            this.delay = delay;
            this.points = points;
        }

        public double getDelay() {
            return delay;
        }

        @Override
        public Iterator<Point2D.Double> iterator() {
            return points.iterator();
        }

    }
}
