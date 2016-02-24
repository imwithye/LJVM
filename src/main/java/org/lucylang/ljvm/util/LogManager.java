package org.lucylang.ljvm.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {
    private static LogManager loggerManager = new LogManager();
    private static Level level = Level.ALL;

    private LogManager() {
    }

    public static LogManager getLoggerManager() {
        return loggerManager;
    }

    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(level);
        return logger;
    }
}
