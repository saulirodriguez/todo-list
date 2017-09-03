package com.agilesolutions.test.service;

import org.apache.log4j.Logger;

public class LoggerManager {
    public enum Level { DEBUG, INFO, WARNING, ERROR, FATAL }

    private static LoggerManager instance= new LoggerManager();
    private Logger log;

    public static LoggerManager getInstance() {
        return instance;
    }

    private LoggerManager() {
        log = Logger.getLogger(LoggerManager.class);
    }

    public void log(String msg){
        log.info(msg);
    }

    public void log(String msg, Level level){
        switch(level){
            case DEBUG:
                log.debug(msg);
                break;
            case INFO:
                log.info(msg);
                break;
            case WARNING:
                log.warn(msg);
                break;
            case ERROR:
                log.error(msg);
                break;
            case FATAL:
                log.fatal(msg);
                break;
            default:
                log.debug(msg);
        }
    }
}
