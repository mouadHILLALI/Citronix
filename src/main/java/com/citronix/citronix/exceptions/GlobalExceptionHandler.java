package com.citronix.citronix.exceptions;

import com.citronix.citronix.exceptions.EntitesCustomExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoFarmWasFoundException.class)
    public ResponseEntity<String> handleNoFarmWasFound(NoFarmWasFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(NoFieldWasFound.class)
    public ResponseEntity<String> handleNoFieldWasFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(FieldSurfaceException.class)
    public ResponseEntity<String> handleFieldSurfaceException(FieldSurfaceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(IllegalFieldsNumber.class)
    public ResponseEntity<String> handleIllegalFieldsNumber(IllegalFieldsNumber ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(FieldsGeneralException.class)
    public ResponseEntity<String> handleFieldsGeneralException(FieldsGeneralException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(TreesDensityException.class)
    public ResponseEntity<String> handleTreesDensityException(TreesDensityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(IllegalPlantationDate.class)
    public ResponseEntity<String> handleIllegalPlantationDate(IllegalPlantationDate ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
