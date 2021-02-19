package com.netcracker.store.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> fields = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                fields.put(fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                fields.put(objectError.getObjectName(), objectError.getDefaultMessage());
            }
        });
        Map<String, Object> map = new HashMap<>();
        map.put("fields", fields);
        map.put("timestamp", ZonedDateTime.now());
        map.put("status", status.value());
        map.put("error", status.getReasonPhrase());
        map.put("message", "");
        map.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity.status(status).body(map);
    }
}
