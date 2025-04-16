// File: GlobalExceptionHandler.java
package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice // tells Spring this handles exceptions globally
public class GlobalExceptionHandler {

    // Handles when a user tries to register with a taken username
    @ExceptionHandler(CustomException.UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameConflict(CustomException.UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Handles bad requests (like missing/empty username)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Catch-all fallback for other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + ex.getMessage());
    }

    @ExceptionHandler(CustomException.BadLoginException.class)
    public ResponseEntity<String> handleBadLogin(CustomException.BadLoginException ex)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
