package com.learn.booking.bookingsystem.controller.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Error {
    private String message;
    private LocalDateTime time;
}