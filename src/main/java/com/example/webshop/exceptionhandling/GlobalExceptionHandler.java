package com.example.webshop.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleBeeNotFound(CustomerNotFoundException exception) {
        ValidationError validationError = new ValidationError("CustomerId",
                "Customer not found with id: " + exception.getCustomerId());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleHiveNotFound(AddressNotFoundException exception) {
        ValidationError validationError = new ValidationError("AddressId",
                "Address not found with id: " + exception.getAddressId());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
