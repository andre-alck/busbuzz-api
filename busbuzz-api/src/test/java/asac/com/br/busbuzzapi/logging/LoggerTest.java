package asac.com.br.busbuzzapi.logging;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerTest {
    ListAppender<ILoggingEvent> appenders = new ListAppender<>();
    Logger logger = (Logger) LoggerFactory.getLogger(LoggerFooBar.class);
    List<ILoggingEvent> logs = appenders.list;

    @BeforeEach
    public void setup() {
        appenders.clearAllFilters();
        appenders.start();

        logger.detachAndStopAllAppenders();
        logger.addAppender(appenders);

        logs.clear();
    }

    @Test
    void givenLoggerFooBarWhenCalledInfoMethodThenShouldLogANewInformationAndVerifyItThroughAppender() {
        LoggerFooBar.info();

        assertEquals("LoggerFooBar.info();", logs.get(0).getMessage());
        assertEquals(Level.INFO.name(), logs.get(0).getLevel().levelStr);
    }

    @Test
    void givenLoggerFooBarWhenCalledDebugMethodThenItShouldNotLogANewDebugMessage() {
        LoggerFooBar.debug();

        assertTrue(logs.isEmpty());
    }

    @Test
    void givenLoggerFooBarWhenCalledErrorMethodThenShouldLogANewErrorAndVerifyItThroughAppender() {
        LoggerFooBar.error();

        assertEquals("LoggerFooBar.error();", logs.get(0).getMessage());
        assertEquals(Level.ERROR.name(), logs.get(0).getLevel().levelStr);
    }
}