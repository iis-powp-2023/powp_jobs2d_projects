package edu.kis.powp.jobs2d.drivers.usage;
import java.util.logging.Logger;

public class LogUsageErrorStrategy implements UsageErrorStrategy {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Override
    public void execute(ErrorType errorType) {
        if(errorType.equals(ErrorType.INK_NEEDS_REFILL)) {
            logger.info("Ink needs to be refilled before performing this operation.");
        } else if(errorType.equals(ErrorType.HEAD_NEEDS_SERVICE)) {
            logger.info("Head needs to be serviced before performing this operation.");
        }
    }
}
