package com.agilesolutions.test.controller;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.ErrorInfo;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.service.LoggerManager;
import com.agilesolutions.test.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo-list")
public class ToDoCtrl {
    private LoggerManager logger = LoggerManager.getInstance();
    private String resource = "ToDo";

    @Autowired
    private ToDoService toDoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ToDo>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
            return ResponseEntity
                    .ok(this.toDoService.findAll(Optional.ofNullable(name), Optional.ofNullable(description)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ToDo> findOne(@PathVariable Long id) throws ResourceNotFoundException {
        ToDo todo = this.toDoService.findOne(id);
        if (todo == null) {
            throw new ResourceNotFoundException(this.resource);
        }

        return ResponseEntity.ok(todo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ToDo> create(@RequestBody ToDo toDo) throws BadRequestException {
        if (toDo.getName() == null || toDo.getName().isEmpty()) {
            throw new BadRequestException(this.resource + " List", "Empty Name");
        }

        if(toDo.getId() != null) {
            toDo.setId(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.toDoService.create(toDo));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ToDo> put(@RequestBody ToDo toDo, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.toDoService.update(id, toDo));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        this.toDoService.delete(id);
    }

    // Exceptions Handling

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ErrorInfo> ResourceNotFoundHandler(HttpServletRequest req, Exception e) {
        this.logError(req, e);
        ErrorInfo error = new ErrorInfo(this.resource, e.getMessage(), req.getMethod(), req.getRequestURI());
        return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorInfo> BadRequestHandler(HttpServletRequest req, Exception e) {
        this.logError(req, e);
        ErrorInfo error = new ErrorInfo(this.resource, e.getMessage(), req.getMethod(), req.getRequestURI());
        return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> GenericErrorHandler(HttpServletRequest req, Exception e) {
        this.logError(req, e);
        ErrorInfo error = new ErrorInfo(this.resource, e.getMessage(), req.getMethod(), req.getRequestURI());
        return new ResponseEntity<ErrorInfo>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private void logError(HttpServletRequest req, Exception e) {
        String reqUri = req.getMethod() + ": " + req.getRequestURI();
        logger.log(reqUri + ":\n" + e.toString(), LoggerManager.Level.ERROR);
    }
}