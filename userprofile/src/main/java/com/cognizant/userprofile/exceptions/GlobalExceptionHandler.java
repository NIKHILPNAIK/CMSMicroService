package com.cognizant.userprofile.exceptions;

import com.cognizant.userprofile.dtos.GenericMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserProfileNullException.class)
    public ResponseEntity<GenericMessage<String>> handleUserProfileNullException(UserProfileNullException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GenericMessage<>(ex.getMessage()));
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<GenericMessage<String>> handleUserProfileNotFoundException(UserProfileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericMessage<>(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericMessage<String>> handleArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        Map<String, String> errors   = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName    = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("Validation Errors", errors.toString());
        response.put("Total Errors", String.valueOf(errors.size()));
        response.put("TimeStamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GenericMessage<>(response.toString()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericMessage<String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new GenericMessage<>(ex.getMessage()));
    }
}