package com.agilesolutions.test.exception;

public class BadRequestException extends Exception  {
    public BadRequestException(String resource, String message) {
        super("Bad Request: " + resource + "; " + message);
    }

    public BadRequestException(String resource) {
        super("Bad Request: " + resource);
    }
}
