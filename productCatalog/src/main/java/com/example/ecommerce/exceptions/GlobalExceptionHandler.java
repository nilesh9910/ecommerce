package com.example.ecommerce.exceptions;

import com.example.ecommerce.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthourisedExcepiton.class)
    ResponseEntity<ErrorDTO> handleUnauthorisedException(UnauthourisedExcepiton ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ArrayList<String>> handleProductNotFound(ProductNotFoundException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
