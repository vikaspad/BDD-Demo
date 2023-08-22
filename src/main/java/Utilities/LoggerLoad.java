package Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

public class LoggerLoad {
    //method to load log4j properties file
    public Logger logger = LogManager.getLogger();

    public void info(String message) {
        logger.info(message);
    }

    public void logPass(String expectedResult, String actualResult) {
        assertEquals(actualResult,expectedResult);
    }

	public void logFail(String message, String expectedResult, String actualResult) {
		assertNotEquals(actualResult,expectedResult);
	}

    public void warn(String message) {
        logger.warn(message);

    }
    public void error(String message) {
        logger.error(message);


    }
    public void fatal(String message) {
        logger.fatal(message);
    }
    public void debug(String message) {
        logger.debug(message);
    }
}
