package com.mauricio.attus.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mauricio.attus.exception.ParteEnvolvidaNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ParteEnvolvidaNotFoundException.class)
  public ResponseEntity<String> handleParteEnvolvidaNotFoundException(ParteEnvolvidaNotFoundException ex) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      var errors = new HashMap<String, String>();
      ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(errors));
  }
}
