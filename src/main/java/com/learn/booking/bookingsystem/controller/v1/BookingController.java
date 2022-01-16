package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.service.BookingFacade;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/booking/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
//TODO add exception handler
//TODO add security
public class BookingController {

    private final BookingFacade bookingService;

    @PostMapping("/event/{eventUuid}/user/{userUuid}")
    public ResponseEntity<OrderResponse> bookToEvent(
        @PathVariable UUID eventUuid, @PathVariable UUID userUuid, @RequestParam Integer numberOfTickets){
        return new ResponseEntity<>(bookingService.bookToEvent(eventUuid, userUuid, numberOfTickets), HttpStatus.OK);
    }

}
