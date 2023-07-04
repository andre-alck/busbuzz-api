package asac.com.br.busbuzzapi.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerFooBar {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFooBar.class);

    public static void info() {
        logger.info("LoggerFooBar.info();");
    }

    public static void error() {
        logger.error("LoggerFooBar.error();");
    }

    public static void debug() {
        logger.debug("LoggerFooBar.debug();");
    }
}
