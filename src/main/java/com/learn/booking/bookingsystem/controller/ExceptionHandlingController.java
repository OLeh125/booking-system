package com.learn.booking.bookingsystem.controller;

import com.learn.booking.bookingsystem.exception.JwtAuthenticationException;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.learn.booking.bookingsystem.controller.model.Error;

@Slf4j
//@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception ex){
        return new Error(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error handleJwtAuthenticationException(JwtAuthenticationException ex){
        return new Error(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleValidationException(NotFoundException ex){
        return new Error(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getAllErrors().stream()
                .map(e -> new Error(e.getDefaultMessage(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
