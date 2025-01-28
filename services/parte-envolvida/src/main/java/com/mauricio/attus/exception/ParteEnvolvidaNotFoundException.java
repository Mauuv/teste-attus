package com.mauricio.attus.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ParteEnvolvidaNotFoundException extends RuntimeException {
  
    private final String message;
}
