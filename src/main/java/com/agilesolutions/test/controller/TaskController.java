package com.agilesolutions.test.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo-list/{todoId}/task")
public class TaskController {
//    private String resource = "Task";
//
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private Utilities utilities;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Task>> findAll(@PathVariable Long todoId, @RequestParam(required = false) String name, @RequestParam(required = false) String description)
//    throws ResourceNotFoundException {
//            return ResponseEntity
//                    .ok(this.taskService.findAll(todoId, Optional.ofNullable(name), Optional.ofNullable(description)));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
//    public ResponseEntity<Task> findById(@PathVariable Long id) throws ResourceNotFoundException {
//        Task task = this.taskService.findById(id);
//
//        return ResponseEntity.ok(task);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Task> create(@RequestBody Task task) throws BadRequestException {
//        if (task.getName() == null || task.getName().isEmpty()) {
//            throw new BadRequestException(this.resource + " List", "Required Field: name");
//        }
//
//        if(task.getId() != null) {
//            task.setId(null);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.save(task));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Long id) throws ResourceNotFoundException {
//        return ResponseEntity.ok(this.taskService.update(id, task));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    public void delete(@PathVariable Long id) {
//        this.taskService.delete(id);
//    }
//
//    // Exceptions Handling
//    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
//    public ResponseEntity<ExceptionResponse> ResourceNotFoundHandler(HttpServletRequest req, Exception e) {
//        return this.utilities.generateError(this.resource, req, e, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler({
//            BadRequestException.class,
//            MethodArgumentTypeMismatchException.class,
//            HttpMessageNotReadableException.class,
//            ClassCastException.class
//    })
//    public ResponseEntity<ExceptionResponse> BadRequestHandler(HttpServletRequest req, Exception e) {
//        return this.utilities.generateError(this.resource, req, e, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> GenericErrorHandler(HttpServletRequest req, Exception e) {
//        return this.utilities.generateError(this.resource, req, e, HttpStatus.SERVICE_UNAVAILABLE);
//    }
}