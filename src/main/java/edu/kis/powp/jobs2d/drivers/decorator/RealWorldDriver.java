package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.Point;
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
    final private int milisecondsInSecond = 1000;

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
        this.setPositionSpeed = 5 * operateToSpeed;
        this.operateToSetDelay = 0.4;
        this.setToOperateDelay = 0.4;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        try {
            if (lastOperationType == OperationType.OPERATE_TO) {
                Thread.sleep((long) (operateToSetDelay * milisecondsInSecond));
            }
            lastOperationType = OperationType.SET_POSITION;
            Thread.sleep((long) (Math.sqrt(Math.pow(x - lastX, 2) + Math.pow(y - lastY, 2)) / setPositionSpeed
                    * milisecondsInSecond));
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
                Thread.sleep((long) (setToOperateDelay * milisecondsInSecond));
            }
            lastOperationType = OperationType.OPERATE_TO;

            LinePartitions operationData = calculateDelays(x, y);
            Double delay = operationData.getDelay();
            for (Point point : operationData) {
                super.operateTo(point.x, point.y);
                Thread.sleep(delay.intValue());
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
        ArrayList<Point> pointsList = new ArrayList<>();
        double delay;
        double distance = Math.sqrt(Math.pow(lastY - destinationY, 2) + Math.pow(lastX - destinationX, 2));
        if (distance < 4) {
            delay = (distance / operateToSpeed);
            Point point = new Point();
            point.x = destinationX;
            point.y = destinationY;
            pointsList.add(point);
            return new LinePartitions(delay * milisecondsInSecond, pointsList);
        }
        int partsNum = (int) distance / 2;
        delay = (distance / operateToSpeed) / partsNum;
        for (int i = 1; i <= partsNum; i++) {
            Point point = new Point();
            point.x = lastX + (destinationX - lastX) / partsNum * i;
            point.y = lastY + (destinationY - lastY) / partsNum * i;
            pointsList.add(point);
        }

        return new LinePartitions(delay * milisecondsInSecond, pointsList);
    }

    private class LinePartitions implements Iterable<Point> {
        private double delay;
        private ArrayList<Point> points;

        public LinePartitions(double delay, ArrayList<Point> points) {
            this.delay = delay;
            this.points = points;
        }

        public double getDelay() {
            return delay;
        }

        @Override
        public Iterator<Point> iterator() {
            return points.iterator();
        }

    }
}
