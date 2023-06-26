package edu.kis.powp.jobs2d.drivers.usage;

public enum ServiceThresholds {
    REFILL_THRESHOLD(5000), HEAD_REPLACE_THRESHOLD(10000);

    private double value;
    ServiceThresholds(double val) {
        this.value = val;
    }

    public double getValue() {
        return value;
    }
}
