package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;

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

    public RealWorldDriver(Job2dDriver driver, double operateToSpeed, double setPositionSpeed, double operateToSetDelay, double setToOperateDelay){
        super(driver);
        this.operateToSpeed = operateToSpeed;
        this.setPositionSpeed = setPositionSpeed;
        this.operateToSetDelay = operateToSetDelay;
        this.setToOperateDelay = setToOperateDelay;
    }

    public RealWorldDriver(Job2dDriver driver, double operateToSpeed){
        super(driver);
        this.operateToSpeed = operateToSpeed;
        this.setPositionSpeed = 3 * operateToSpeed;
        this.operateToSetDelay = 1.0;
        this.setToOperateDelay = 1.0;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x,y);
        try {
            if(lastOperationType == OperationType.OPERATE_TO){
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
            if(lastOperationType == OperationType.SET_POSITION){
                Thread.sleep((long) (setToOperateDelay * 1000));
            }
            lastOperationType = OperationType.OPERATE_TO;
            ArrayList<Double> operationData = calculateDelays(x, y);
            Double delay = operationData.get(0);
            for (int i = 1; i < operationData.size(); i += 2) {
                if (i != operationData.size() - 1) {
                    Thread.sleep(delay.intValue());
                }
                super.operateTo(operationData.get(i).intValue(), operationData.get(i + 1).intValue());
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

    private ArrayList<Double> calculateDelays(int destinationX, int destinationY) {
        ArrayList<Double> output = new ArrayList<>();
        double distance = Math.sqrt(Math.pow(lastY - destinationY, 2) + Math.pow(lastX - destinationX, 2));
        double delay = (distance/operateToSpeed)/partsNum;
        output.add(delay);
        for (int i = 1; i <= partsNum; i++) {
            double x = lastX + (double) (destinationX - lastX) / partsNum * i;
            double y = lastY + (double) (destinationY - lastY) / partsNum * i;
            output.add(x);
            output.add(y);
        }
        return output;
    }
}
