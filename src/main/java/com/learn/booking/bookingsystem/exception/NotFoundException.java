package com.learn.booking.bookingsystem.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

}
