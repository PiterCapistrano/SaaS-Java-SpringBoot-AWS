package com.meusaas.usuarios.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

  @ExceptionHandler(EmailDuplicadoException.class)
  public ResponseEntity<String> handleEmailDuplicadoException(EmailDuplicadoException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
  
}
