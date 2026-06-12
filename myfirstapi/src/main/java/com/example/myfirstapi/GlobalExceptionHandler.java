package com.example.myfirstapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex)
    {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDate.now());
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found Bhai");
        errorBody.put("message", ex.getMessage());
        return new ResponseEntity<>(errorBody
                , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        Map<String , Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", java.time.LocalDateTime.now());
        errorBody.put("status" , HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", "Validation Failed Bhai");
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->
        {
            String fieldName = ((org.springframework.validation.FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        errorBody.put("validation_errors", fieldErrors);
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResourceNotFoundException<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex)
//    {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", ex.getMessage());
//        return new ResourceNotFoundException(error, HttpStatus.NOT_FOUND);
//
//    }
}
