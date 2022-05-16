package com.learn.task.management.task2.controller;

import com.learn.task.management.task2.service.SocialNetworkService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/network", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SocialNetworkController {

    private final SocialNetworkService socialNetworkService;

    @GetMapping(value = "/movies/min", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Long> getMinMovies() {
        return new ResponseEntity<>(socialNetworkService.minNumberOfWatchedMoviesByUsersMore100Friends(), HttpStatus.OK);
    }

    @GetMapping(value = "/friendships/max")
    public ResponseEntity<Long> getMaxFriendships(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime to) {
        return new ResponseEntity<>(socialNetworkService.maxNumberOfNewFriendships(from, to), HttpStatus.OK);
    }

    @GetMapping(value = "/messages/avg", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Double> getAverageNumberOfMessagesByDay(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate day) {
        return new ResponseEntity<>(socialNetworkService.averageNumberOfMessagesByDay(day), HttpStatus.OK);
    }

    @PostMapping(value = "/data")
    public ResponseEntity<Void> fillTestData() {
        socialNetworkService.fillTestData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
