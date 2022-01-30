package com.learn.booking.bookingsystem.controller.model.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;

}
