package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class PositionLoggingDriver implements Job2dDriver {
    Logger logger = Logger.getLogger("global");
    int operationCount = 0;

    public PositionLoggingDriver() {
    }

    public void setPosition(int x, int y) {
        this.logger.info("(" + x + ", " + y + ")\tSetting position\t  [#" + (++operationCount) + "]");
    }

    public void operateTo(int x, int y) {
        this.logger.info("(" + x + ", " + y + ")\tOperating\t  [#" + (++operationCount) + "]");
    }

    public String toString() {
        return "Logger driver";
    }
}
