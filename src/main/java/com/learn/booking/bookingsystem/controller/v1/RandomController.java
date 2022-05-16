package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/random", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RandomController {

    private final RandomService randomService;

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> addRandomUsers() {
        randomService.addRandomUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
