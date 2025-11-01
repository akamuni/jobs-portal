package com.example.appdocs.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("error", "Validation failed");
        Map<String,String> fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> fields.put(fe.getField(), fe.getDefaultMessage()));
        body.put("fields", fields);
        return body;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleConstraint(ConstraintViolationException ex) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("error", "Constraint violation");
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleNotFound(RuntimeException ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("error", "Not Found");
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("error", "Bad Request");
        map.put("message", "Invalid parameter: " + ex.getName());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleOther(Exception ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("error", "Server Error");
        map.put("message", ex.getMessage());
        return map;
    }
}
