package com.mauricio.attus.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mauricio.attus.exception.BussinesException;
import com.mauricio.attus.exception.ContratoNotFoundException;
import com.mauricio.attus.exception.EventoNotFoundException;
import com.mauricio.attus.exception.InvalidParteEnvolvidaException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidParteEnvolvidaException.class)
  public ResponseEntity<String> handleInvalidParteEnvolvidaException(InvalidParteEnvolvidaException ex) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(ex.getMessage());
  }

  @ExceptionHandler(ContratoNotFoundException.class)
  public ResponseEntity<String> handleContratoNotFoundException(ContratoNotFoundException ex) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(ex.getMessage());
  }

  @ExceptionHandler(EventoNotFoundException.class)
  public ResponseEntity<String> handleEventoNotFoundException(EventoNotFoundException ex) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(ex.getMessage());
  }

  @ExceptionHandler(BussinesException.class)
  public ResponseEntity<String> handleBussinesException(BussinesException ex) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
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
