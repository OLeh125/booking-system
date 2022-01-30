package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.ticket.request.CreateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.request.UpdateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.service.TicketService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/tickets/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TicketController {

    private final TicketService TicketService;

    @GetMapping("/{ticketUuid}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable UUID ticketUuid){
        return new ResponseEntity<>(TicketService.getTicket(ticketUuid), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<TicketResponse> createTicket(@RequestBody CreateTicketRequest ticketRequest){
        return new ResponseEntity<>(TicketService.createTicket(ticketRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{ticketUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<TicketResponse> updateTicket(@RequestBody UpdateTicketRequest ticketRequest, @PathVariable UUID ticketUuid){
        return new ResponseEntity<>(TicketService.updateTicket(ticketRequest, ticketUuid), HttpStatus.OK);
    }

    @DeleteMapping("/{ticketUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketUuid){
        TicketService.deleteTicket(ticketUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
