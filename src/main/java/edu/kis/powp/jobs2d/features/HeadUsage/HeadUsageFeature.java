package edu.kis.powp.jobs2d.features.HeadUsage;

public class HeadUsageFeature {
    private static final double MAX_INK_LEVEL = 5000.0;
    private double headUsage;
    private double usageLevel;
    private int x;
    private int y;
    private double headDistance;
    private double operationDistance;

    HeadUsageFeature() {
        this.headUsage = 0;
        this.usageLevel = 0.0;
        this.x = 0;
        this.y = 0;
        this.headDistance = 0.0;
        this.operationDistance = 0.0;
    }

    public double getHeadDistance() {
        return headDistance;
    }

    public double getOperationDistance() {
        return operationDistance;
    }

    public void saveCoordinates(int x, int y) {
        headDistance += calculateDistance(x, y);
        this.x = x;
        this.y = y;
    }

    private double calculateDistance(int x, int y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    private void calculateInkUsage(int x, int y) {
        headUsage += calculateDistance(x, y);
    }

    public void fillPlotterWithInk(double inkAmount) {
        if (inkAmount < 0) {
            return;
        }

        usageLevel += inkAmount;
        if (usageLevel > MAX_INK_LEVEL) {
            usageLevel = MAX_INK_LEVEL;
        }
    }

    public boolean checkTonerLevel(int x, int y) {
        calculateInkUsage(x, y);
        headDistance += headUsage;
        
        usageLevel -= headUsage;
        if (usageLevel <= 0) {
            headUsage = 0;
            return false;
        }

        operationDistance += headUsage;
        headUsage = 0;
        return true;
    }
}

