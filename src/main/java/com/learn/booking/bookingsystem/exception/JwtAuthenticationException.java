package com.learn.booking.bookingsystem.exception;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
