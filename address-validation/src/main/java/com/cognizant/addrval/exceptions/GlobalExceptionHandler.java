package com.cognizant.addrval.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {
    	
    	
        Map<String,Object> body = new LinkedHashMap<>();
        Map<String,String> errors = new LinkedHashMap<>();
        
        
        ex.getBindingResult().getFieldErrors()
          .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        
        
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("error", "Validation failed");
        body.put("errors", errors);
        body.put("count", errors.size());
        return ResponseEntity.badRequest().body(body);
    }
}