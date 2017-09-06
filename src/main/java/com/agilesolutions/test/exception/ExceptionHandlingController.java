package com.agilesolutions.test.exception;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlingController {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(HttpServletRequest req, Exception e) {
        return this.generateError(req, e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            ClassCastException.class
    })
    public ResponseEntity<ExceptionResponse> badRequestHandler(HttpServletRequest req, Exception e) {
        return this.generateError(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericErrorHandler(HttpServletRequest req, Exception e) {
        return this.generateError(req, e, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<ExceptionResponse> generateError(HttpServletRequest req, Exception e, HttpStatus status) {
        LOGGER.error(req.getMethod() + ": " + req.getRequestURI());
        LOGGER.error(e.toString());

        ExceptionResponse error = new ExceptionResponse(e.getMessage(), req.getMethod(), req.getRequestURI());
        return new ResponseEntity<>(error, status);
    }
}