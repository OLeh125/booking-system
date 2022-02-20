package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.service.TransactionService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("users/{userUuid}/allInOne/{accountUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> getTicket(@PathVariable UUID userUuid, @PathVariable UUID accountUuid) {
        transactionService.transactAllInOneAccount(userUuid, accountUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
