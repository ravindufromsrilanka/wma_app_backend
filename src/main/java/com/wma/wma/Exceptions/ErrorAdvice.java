package com.wma.wma.Exceptions;

import com.wma.wma.Payload.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(ErrorException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .variable(ex.getVariable()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }
}
