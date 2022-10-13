package com.example.bgame;

import com.example.bgame.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Collections;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BoardGameNotFoundException.class, RoleNotFoundException.class, BoardGameListNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExternalApiConnectionException.class})
    public ResponseEntity<Object> handleConnectionException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler({UsernameTakenException.class, EmailAlreadyTakenException.class})
    public ResponseEntity<Object> handleEntityAlreadyTaken(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
