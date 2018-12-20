package com.synergysuite.hrmservice.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ResponseEntity toResponse(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(super.getMessage());
    }
}
