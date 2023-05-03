package edu.kis.powp.jobs2d.features.InkUsage;

sealed public class InkUsageFeature permits InkUsageFeatureManager {
    private static final double MAX_INK_LEVEL = 5000.0;
    private double inkUsage;
    private double inkLevel;
    private int x;
    private int y;
    private double headDistance;
    private double operationDistance;

    InkUsageFeature() {
        this.inkUsage = 0;
        this.inkLevel = 0.0;
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
        inkUsage += calculateDistance(x, y);
    }

    public void fillPlotterWithInk(double inkAmount) {
        if (inkAmount < 0) {
            return;
        }

        inkLevel += inkAmount;
        if (inkLevel > MAX_INK_LEVEL) {
            inkLevel = MAX_INK_LEVEL;
        }
    }

    public boolean checkTonerLevel(int x, int y) {
        calculateInkUsage(x, y);
        headDistance += inkUsage;

        inkLevel -= inkUsage;
        if (inkLevel <= 0) {
            inkUsage = 0;
            return false;
        }

        operationDistance += inkUsage;
        inkUsage = 0;
        return true;
    }
}

