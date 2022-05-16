package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.service.mongo.MongoMigrationFacade;
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
@RequestMapping(value = "/api/v1/migrate", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MigrationController {

    private final MongoMigrationFacade migrationFacade;

    @PostMapping("/all")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateAllData() {
        migrationFacade.migrateAllData();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateUsers() {
        migrationFacade.migrateAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/events")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateEvents() {
        migrationFacade.migrateAllEvents();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/accounts")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateAccounts() {
        migrationFacade.migrateAllAccounts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tickets")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateTickets() {
        migrationFacade.migrateAllTickets();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/orders")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> migrateOrders() {
        migrationFacade.migrateAllOrders();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}