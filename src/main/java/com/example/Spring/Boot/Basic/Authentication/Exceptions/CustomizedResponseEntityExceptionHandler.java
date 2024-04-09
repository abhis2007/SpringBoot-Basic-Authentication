package com.example.Spring.Boot.Basic.Authentication.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest web){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), web.getDescription(false)) ;
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler({UserNotFoundException.class, UsernameNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest web){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), web.getDescription(false)) ;
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND) ;
    }

}
